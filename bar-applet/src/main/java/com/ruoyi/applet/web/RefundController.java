package com.ruoyi.applet.web;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.ruoyi.applet.config.DomainConfig;
import com.ruoyi.applet.domain.Merchant;
import com.ruoyi.applet.domain.Orders;
import com.ruoyi.applet.domain.Refund;
import com.ruoyi.applet.domain.User;
import com.ruoyi.applet.model.dto.RefundCreateDTO;
import com.ruoyi.applet.service.MerchantService;
import com.ruoyi.applet.service.OrdersService;
import com.ruoyi.applet.service.RefundService;
import com.ruoyi.applet.service.UserService;
import com.ruoyi.applet.support.utils.CalculateUtils;
import com.ruoyi.applet.support.wxpay.AppletWXPayConfig;
import com.ruoyi.applet.support.wxpay.WXPay;
import com.ruoyi.applet.support.wxpay.WXPayConstants;
import com.ruoyi.applet.support.wxpay.WXPayUtil;
import com.ruoyi.common.core.domain.AppletResult;
import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.common.enums.RefundStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 退款 前端控制器
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-16
 */
@Api(tags = "退款相关接口")
@RestController
@RequestMapping("/refund")
@Slf4j
public class RefundController {

    @Autowired
    private RefundService refundService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private DomainConfig domainConfig;

    /**
     * 校验获取退款金额
     *
     * @return
     */
    @ApiOperation("校验获取退款金额接口")
    @GetMapping("/refundAmount")
    public AppletResult refundAmount(@RequestParam("orderId") Long orderId) {
        // 获取订单信息
        Orders orders = ordersService.getById(orderId);
        if (!Optional.ofNullable(orders).isPresent()) {
            throw new ServiceException("订单不存在");
        }
        if (orders.getStatus() != OrderStatus.PAYMENT.getStatus()) {
            throw new ServiceException("只有未完成状态的订单允许发起退款");
        }
        // 获取商家信息
        Merchant merchant = merchantService.getById(orders.getMerchantId());
        if (!Optional.ofNullable(merchant).isPresent()) {
            throw new ServiceException("商家不存在");
        }
        return AppletResult.success(refundService.refundAmount(orders));
    }

    /**
     * 申请退款
     *
     * @return
     */
    @ApiOperation("申请退款接口")
    @PostMapping("/applyRefund")
    public AppletResult applyRefund(@RequestBody @Validated RefundCreateDTO refundCreateDTO) throws Exception {
        // 获取订单信息
        Orders orders = ordersService.getById(refundCreateDTO.getOrderId());
        if (!Optional.ofNullable(orders).isPresent()) {
            throw new ServiceException("订单不存在");
        }
        if (orders.getStatus() != OrderStatus.PAYMENT.getStatus()) {
            throw new ServiceException("只有未完成状态的订单允许发起退款");
        }
        // 获取商家信息
        Merchant merchant = merchantService.getById(orders.getMerchantId());
        if (!Optional.ofNullable(merchant).isPresent()) {
            throw new ServiceException("商家不存在");
        }

        // 再次计算实际的退款金额
        BigDecimal refundAmount = refundService.refundAmount(orders);

        // 获取当前登录用户
        User user = userService.getUserByRequest(request);

        // 退款号
        Long refundNo = IdWorker.getId();
        Refund refund = new Refund();
        refund.setRefundNo(refundNo);
        refund.setUserId(user.getId());
        refund.setMerchantId(merchant.getId());
        refund.setOrderId(orders.getId());
        // 退款金额
        refund.setRefundAmount(refundAmount);
        // 退款原因
        refund.setReason(refundCreateDTO.getReason());
        refund.setReasonDetail(refundCreateDTO.getReasonDetail());
        refund.setStatus(RefundStatus.SUBMIT.getStatus());
        refund.setCreateTime(LocalDateTime.now());
        refund.setUpdateTime(LocalDateTime.now());
        boolean flag = refundService.save(refund);
        if (flag) {
            // 修改订单状态为退款中
            UpdateWrapper<Orders> orderUpdateWrapper = new UpdateWrapper<>();
            orderUpdateWrapper.set("status", OrderStatus.REFUND.getStatus());
            orderUpdateWrapper.set("update_time", LocalDateTime.now());
            orderUpdateWrapper.eq("id", orders.getId());
            orderUpdateWrapper.eq("status", OrderStatus.PAYMENT.getStatus());
            ordersService.update(orderUpdateWrapper);
        }

        // 退款回调地址
        String notifyUrl = domainConfig.getAppletUrl() + "/refund/notifyRefund";
        // 异步发起退款
        refundService.applyRefund(refund, notifyUrl);
        return AppletResult.success(refund);
    }

    /**
     * 退款回调
     *
     * @return
     */
    @ApiOperation("退款回调接口")
    @PostMapping("/notifyRefund")
    public String notifyRefund() throws Exception {
        // 回调数据
        String resultXml = ServletUtils.requestStreamToString(request);
        Map<String, String> results = WXPayUtil.xmlToMap(resultXml);
        log.info("微信退款回调，响应数据：{}", JSON.toJSONString(results));

        // 获取通信标识
        String return_code = results.get("return_code");
        if (!"SUCCESS".equals(return_code)) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("return_code", WXPayConstants.FAIL);
            responseMap.put("return_msg", "通信失败");
            String responseXml = WXPayUtil.mapToXml(responseMap);
            return responseXml;
        }

        // 验证签名
        AppletWXPayConfig appletWXPayConfig = new AppletWXPayConfig();
        boolean isSignatureValid = WXPayUtil.isSignatureValid(results, appletWXPayConfig.getKey(), WXPayConstants.SignType.MD5);
        if (!isSignatureValid) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("return_code", WXPayConstants.FAIL);
            responseMap.put("return_msg", "签名验证不通过");
            String responseXml = WXPayUtil.mapToXml(responseMap);
            return responseXml;
        }

        // 获取加密信息
        String req_info = results.get("req_info");
        String req_info_xml = WXPayUtil.decryptData(req_info, appletWXPayConfig.getKey());
        Map<String, String> reqInfoMap = WXPayUtil.xmlToMap(req_info_xml);

        // 商户退款单号
        String out_refund_no = reqInfoMap.get("out_refund_no");
        // 退款金额，单位为分
        String refund_fee = results.get("refund_fee");

        QueryWrapper<Refund> refundQueryWrapper = new QueryWrapper<>();
        refundQueryWrapper.eq("refund_no", out_refund_no);
        Refund refund = refundService.getOne(refundQueryWrapper);
        // 退款单不存在
        if (!Optional.ofNullable(refund).isPresent()) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("return_code", WXPayConstants.FAIL);
            responseMap.put("return_msg", "退款单不存在");
            String responseXml = WXPayUtil.mapToXml(responseMap);
            return responseXml;
        }

        // 校验退款金额
        if (!Integer.valueOf(refund_fee).equals(CalculateUtils.yuanToFen(refund.getRefundAmount()))) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("return_code", WXPayConstants.FAIL);
            responseMap.put("return_msg", "退款金额不一致");
            String responseXml = WXPayUtil.mapToXml(responseMap);
            return responseXml;
        }

        // 如果退款单状态是退款成功或退款失败直接响应成功
        if (refund.getStatus().equals(RefundStatus.SUCCESS.getStatus()) || refund.getStatus().equals(RefundStatus.FAIL.getStatus())) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("return_code", WXPayConstants.SUCCESS);
            responseMap.put("return_msg", "OK");
            String responseXml = WXPayUtil.mapToXml(responseMap);
            return responseXml;
        }

        // 退款状态
        String refund_status = reqInfoMap.get("refund_status");
        if ("SUCCESS".equals(refund_status)) {
            // 修改退款中的退款单为退款成功
            UpdateWrapper<Refund> refundUpdateWrapper = new UpdateWrapper<>();
            refundUpdateWrapper.set("status", RefundStatus.SUCCESS.getStatus());
            refundUpdateWrapper.set("update_time", LocalDateTime.now());
            refundUpdateWrapper.eq("id", refund.getId());
            refundUpdateWrapper.eq("status", RefundStatus.REFUND.getStatus());
            boolean updateResult = refundService.update(refundUpdateWrapper);
            if (updateResult) {
                // 修改订单为退款成功
                UpdateWrapper<Orders> orderUpdateWrapper = new UpdateWrapper<>();
                orderUpdateWrapper.set("status", OrderStatus.REFUNDED.getStatus());
                orderUpdateWrapper.set("update_time", LocalDateTime.now());
                orderUpdateWrapper.eq("id", refund.getOrderId());
                orderUpdateWrapper.eq("status", OrderStatus.REFUND.getStatus());
                ordersService.update(orderUpdateWrapper);
                // 扣减商家余额
                merchantService.decreaseBalance(refund.getMerchantId(), refund.getRefundAmount());
            }
        } else if ("CHANGE".equals(refund_status)) {
            // 退款异常
            UpdateWrapper<Refund> refundUpdateWrapper = new UpdateWrapper<>();
            refundUpdateWrapper.set("status", RefundStatus.FAIL.getStatus());
            refundUpdateWrapper.set("fail_reason", "退款异常");
            refundUpdateWrapper.set("update_time", LocalDateTime.now());
            refundUpdateWrapper.eq("id", refund.getId());
            refundUpdateWrapper.eq("status", RefundStatus.REFUND.getStatus());
            boolean updateResult = refundService.update(refundUpdateWrapper);
            if (updateResult) {
                // 修改订单为退款失败
                UpdateWrapper<Orders> orderUpdateWrapper = new UpdateWrapper<>();
                orderUpdateWrapper.set("status", OrderStatus.REFUND_FAIL.getStatus());
                orderUpdateWrapper.set("update_time", LocalDateTime.now());
                orderUpdateWrapper.eq("id", refund.getOrderId());
                orderUpdateWrapper.eq("status", OrderStatus.REFUND.getStatus());
                ordersService.update(orderUpdateWrapper);
            }
        } else if ("REFUNDCLOSE".equals(refund_status)) {
            // 退款关闭
            UpdateWrapper<Refund> refundUpdateWrapper = new UpdateWrapper<>();
            refundUpdateWrapper.set("status", RefundStatus.FAIL.getStatus());
            refundUpdateWrapper.set("fail_reason", "退款关闭");
            refundUpdateWrapper.set("update_time", LocalDateTime.now());
            refundUpdateWrapper.eq("id", refund.getId());
            refundUpdateWrapper.eq("status", RefundStatus.REFUND.getStatus());
            boolean updateResult = refundService.update(refundUpdateWrapper);
            if (updateResult) {
                // 修改订单为退款失败
                UpdateWrapper<Orders> orderUpdateWrapper = new UpdateWrapper<>();
                orderUpdateWrapper.set("status", OrderStatus.REFUND_FAIL.getStatus());
                orderUpdateWrapper.set("update_time", LocalDateTime.now());
                orderUpdateWrapper.eq("id", refund.getOrderId());
                orderUpdateWrapper.eq("status", OrderStatus.REFUND.getStatus());
                ordersService.update(orderUpdateWrapper);
            }
        } else {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("return_code", WXPayConstants.FAIL);
            responseMap.put("return_msg", "退款状态异常");
            String responseXml = WXPayUtil.mapToXml(responseMap);
            return responseXml;
        }

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("return_code", WXPayConstants.SUCCESS);
        responseMap.put("return_msg", "OK");
        String responseXml = WXPayUtil.mapToXml(responseMap);
        return responseXml;
    }

    /**
     * 退款查单
     *
     * @return
     */
    @ApiOperation("退款查单接口")
    @PostMapping("/refundQuery")
    public AppletResult refundQuery(@RequestParam("refundId") Long refundId) throws Exception {
        // 获取订单信息
        Refund refund = refundService.getById(refundId);
        if (!Optional.ofNullable(refund).isPresent()) {
            throw new ServiceException("退款单不存在");
        }
        if (refund.getStatus() != RefundStatus.REFUND.getStatus()) {
            throw new ServiceException("只有退款中状态的退款单才允许查单");
        }

        AppletWXPayConfig appletWXPayConfig = new AppletWXPayConfig();
        WXPay wxPay = new WXPay(appletWXPayConfig, null, false, false);

        Map<String, String> params = new HashMap<>();
        params.put("out_refund_no", refund.getRefundNo().toString());

        // 调用查询退款接口
        Map<String, String> results = wxPay.refundQuery(params);
        log.info("微信退款查单接口，响应数据：", JSON.toJSONString(results));
        // 获取通信标识
        String return_code = results.get("return_code");
        if (!return_code.equals(WXPayConstants.SUCCESS)) {
            throw new ServiceException("查询退款通信失败");
        }
        // 校验业务响应
        String result_code = results.get("result_code");
        if (!result_code.equals(WXPayConstants.SUCCESS)) {
            throw new ServiceException("查询退款业务失败");
        }
        // 退款状态
        String refund_status_0 = results.get("refund_status_0");
        if ("SUCCESS".equals(refund_status_0)) {
            // 修改退款中的退款单为退款成功
            UpdateWrapper<Refund> refundUpdateWrapper = new UpdateWrapper<>();
            refundUpdateWrapper.set("status", RefundStatus.SUCCESS.getStatus());
            refundUpdateWrapper.set("update_time", LocalDateTime.now());
            refundUpdateWrapper.eq("id", refund.getId());
            refundUpdateWrapper.eq("status", RefundStatus.REFUND.getStatus());
            boolean updateResult = refundService.update(refundUpdateWrapper);
            if (updateResult) {
                // 修改订单为退款成功
                UpdateWrapper<Orders> orderUpdateWrapper = new UpdateWrapper<>();
                orderUpdateWrapper.set("status", OrderStatus.REFUNDED.getStatus());
                orderUpdateWrapper.set("update_time", LocalDateTime.now());
                orderUpdateWrapper.eq("id", refund.getOrderId());
                orderUpdateWrapper.eq("status", OrderStatus.REFUND.getStatus());
                ordersService.update(orderUpdateWrapper);
                // 扣减商家余额
                merchantService.decreaseBalance(refund.getMerchantId(), refund.getRefundAmount());
            }
        } else if ("CHANGE".equals(refund_status_0)) {
            // 退款异常
            UpdateWrapper<Refund> refundUpdateWrapper = new UpdateWrapper<>();
            refundUpdateWrapper.set("status", RefundStatus.FAIL.getStatus());
            refundUpdateWrapper.set("fail_reason", "退款异常");
            refundUpdateWrapper.set("update_time", LocalDateTime.now());
            refundUpdateWrapper.eq("id", refund.getId());
            refundUpdateWrapper.eq("status", RefundStatus.REFUND.getStatus());
            boolean updateResult = refundService.update(refundUpdateWrapper);
            if (updateResult) {
                // 修改订单为退款失败
                UpdateWrapper<Orders> orderUpdateWrapper = new UpdateWrapper<>();
                orderUpdateWrapper.set("status", OrderStatus.REFUND_FAIL.getStatus());
                orderUpdateWrapper.set("update_time", LocalDateTime.now());
                orderUpdateWrapper.eq("id", refund.getOrderId());
                orderUpdateWrapper.eq("status", OrderStatus.REFUND.getStatus());
                ordersService.update(orderUpdateWrapper);
            }
        } else if ("REFUNDCLOSE".equals(refund_status_0)) {
            // 退款关闭
            UpdateWrapper<Refund> refundUpdateWrapper = new UpdateWrapper<>();
            refundUpdateWrapper.set("status", RefundStatus.FAIL.getStatus());
            refundUpdateWrapper.set("fail_reason", "退款关闭");
            refundUpdateWrapper.set("update_time", LocalDateTime.now());
            refundUpdateWrapper.eq("id", refund.getId());
            refundUpdateWrapper.eq("status", RefundStatus.REFUND.getStatus());
            boolean updateResult = refundService.update(refundUpdateWrapper);
            if (updateResult) {
                // 修改订单为退款失败
                UpdateWrapper<Orders> orderUpdateWrapper = new UpdateWrapper<>();
                orderUpdateWrapper.set("status", OrderStatus.REFUND_FAIL.getStatus());
                orderUpdateWrapper.set("update_time", LocalDateTime.now());
                orderUpdateWrapper.eq("id", refund.getOrderId());
                orderUpdateWrapper.eq("status", OrderStatus.REFUND.getStatus());
                ordersService.update(orderUpdateWrapper);
            }
        } else if ("PROCESSING".equals(refund_status_0)) {
            log.info("退款单ID:{},退款处理中", refundId);
        } else {
            log.error("退款单ID:{},退款状态：{}异常", refundId, refund_status_0);
        }

        // 响应给小程序的信息
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("refund_status", refund_status_0);
        return AppletResult.success(resultMap);
    }

}