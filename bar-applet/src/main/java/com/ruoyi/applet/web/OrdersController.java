package com.ruoyi.applet.web;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.applet.config.AppletConfig;
import com.ruoyi.applet.config.DomainConfig;
import com.ruoyi.applet.config.RuoYiAppletConfig;
import com.ruoyi.applet.domain.*;
import com.ruoyi.applet.model.dto.*;
import com.ruoyi.applet.service.*;
import com.ruoyi.applet.support.utils.CalculateUtils;
import com.ruoyi.applet.support.utils.FileUploadUtils;
import com.ruoyi.applet.support.utils.QRCodeUtils;
import com.ruoyi.applet.support.wxpay.AppletWXPayConfig;
import com.ruoyi.applet.support.wxpay.WXPay;
import com.ruoyi.applet.support.wxpay.WXPayConstants;
import com.ruoyi.applet.support.wxpay.WXPayUtil;
import com.ruoyi.common.core.domain.AppletResult;
import com.ruoyi.common.enums.*;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.IpUtils;
import com.ruoyi.common.utils.LocalDateTimeUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-13
 */
@Api(tags = "订单相关接口")
@RestController
@RequestMapping("/order")
@Slf4j
public class OrdersController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private RefundService refundService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantTopicService merchantTopicService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private AppletConfig appletConfig;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private DomainConfig domainConfig;

    /**
     * 分页查询订单列表
     *
     * @param orderQueryDTO
     * @param page
     * @return
     */
    @ApiOperation("分页查询订单列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页大小", dataType = "int", paramType = "query", defaultValue = "10")
    })
    @GetMapping("/page")
    public AppletResult page(OrderQueryDTO orderQueryDTO, @ApiIgnore Page<Orders> page) {
        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();

        // 获取当前登录用户
        User user = userService.getUserByRequest(request);
        ordersQueryWrapper.eq("user_id", user.getId());
        ordersQueryWrapper.eq("delete_flag", DeleteFlag.NO_DELETE.getDeleteFlag());

        if (orderQueryDTO.getOrderType() == 1) {
            ordersQueryWrapper.eq("status", OrderStatus.NON_PAYMENT.getStatus());
        } else if (orderQueryDTO.getOrderType() == 2) {
            ordersQueryWrapper.eq("status", OrderStatus.PAYMENT.getStatus());
        } else if (orderQueryDTO.getOrderType() == 3) {
            ordersQueryWrapper.eq("status", OrderStatus.DONE.getStatus());
        } else if (orderQueryDTO.getOrderType() == 4) {
            List<Integer> statusList = new ArrayList<>();
            statusList.add(OrderStatus.REFUND.getStatus());
            statusList.add(OrderStatus.REFUNDED.getStatus());
            statusList.add(OrderStatus.REFUND_FAIL.getStatus());
            statusList.add(OrderStatus.PAYMENT_FAIL.getStatus());
            statusList.add(OrderStatus.INVALID.getStatus());
            ordersQueryWrapper.in("status", statusList);
        }
        ordersQueryWrapper.orderByDesc("create_time");
        Page<Orders> orderPage = ordersService.page(page, ordersQueryWrapper);
        for (Orders order : orderPage.getRecords()) {
            // 状态描述
            order.setStatusDesc(OrderStatus.getDescByStatus(order.getStatus()));
            // 商家信息
            Merchant merchant = merchantService.getById(order.getMerchantId());
            if (Optional.ofNullable(merchant).isPresent()) {
                order.setMechantName(merchant.getName());
                order.setMechantLogo(merchant.getLogo());
                order.setMechantContact(merchant.getContact());
            }
        }
        return AppletResult.success(orderPage);
    }

    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    @ApiOperation("查询订单详情")
    @GetMapping("/detail")
    public AppletResult detail(@RequestParam("orderId") Long orderId) {
        Orders orders = ordersService.getById(orderId);
        // 状态描述
        orders.setStatusDesc(OrderStatus.getDescByStatus(orders.getStatus()));
        // 商家信息
        Merchant merchant = merchantService.getById(orders.getMerchantId());
        if (Optional.ofNullable(merchant).isPresent()) {
            orders.setMechantName(merchant.getName());
            orders.setMechantLogo(merchant.getLogo());
            orders.setMechantContact(merchant.getContact());
            // 查询商家话题
            QueryWrapper<MerchantTopic> merchantTopicQueryWrapper = new QueryWrapper<>();
            merchantTopicQueryWrapper.eq("merchant_id", merchant.getId());
            merchantTopicQueryWrapper.orderByAsc("sort");
            List<MerchantTopic> merchantTopics = merchantTopicService.list(merchantTopicQueryWrapper);
            if (CollUtil.isNotEmpty(merchantTopics)) {
                orders.setMerchantTopics(merchantTopics);
            }
        }
        // 退款状态信息
        if (orders.getStatus().equals(OrderStatus.REFUND.getStatus())
                || orders.getStatus().equals(OrderStatus.REFUNDED.getStatus())
                || orders.getStatus().equals(OrderStatus.REFUND_FAIL.getStatus())) {
            // 查询退款信息
            QueryWrapper<Refund> refundQueryWrapper = new QueryWrapper<>();
            refundQueryWrapper.eq("order_id", orders.getId());
            Refund refund = refundService.getOne(refundQueryWrapper);
            if (Optional.ofNullable(refund).isPresent()) {
                orders.setRefundAmount(refund.getRefundAmount());
                orders.setRefundReason(refund.getReason());
                orders.setRefundReasonDetail(refund.getReasonDetail());
                orders.setRefundStatus(refund.getStatus());
            }
        }

        return AppletResult.success(orders);
    }

    /**
     * 重新支付订单接口
     *
     * @return
     */
    @ApiOperation("重新支付订单接口")
    @PostMapping("/repayOrder")
    public AppletResult repayOrder(@RequestBody @Validated OrderRepayDTO orderRepayDTO) {
        // 获取订单信息
        Orders orders = ordersService.getById(orderRepayDTO.getOrderId());
        if (!Optional.ofNullable(orders).isPresent()) {
            throw new ServiceException("订单不存在");
        }
        if (orders.getStatus() != OrderStatus.PAYMENT_FAIL.getStatus()) {
            throw new ServiceException("只有支付失败状态的订单允许重新支付");
        }
        if (!orders.getGoodsType().equals(GoodsType.PACKAGE.getType())) {
            if (LocalDateTime.now().isAfter(orders.getValidEndTime())) {
                throw new ServiceException("订单有效期已过，不允许重新支付");
            }
        }
        // 获取商家信息
        Merchant merchant = merchantService.getById(orders.getMerchantId());
        if (!Optional.ofNullable(merchant).isPresent()) {
            throw new ServiceException("商家不存在");
        }
        // 获取商品信息
        Goods goods = goodsService.getById(orders.getGoodsId());
        if (!Optional.ofNullable(goods).isPresent()) {
            throw new ServiceException("商品不存在");
        }
        if (goods.getStatus() != GoodsStatus.SOLD_ON.getStatus()) {
            throw new ServiceException("商品未上架，不能购买");
        }
        if (goods.getQuantity() <= 0) {
            throw new ServiceException("商品已售完");
        }
        UpdateWrapper<Orders> orderUpdateWrapper = new UpdateWrapper<>();
        orderUpdateWrapper.set("status", OrderStatus.NON_PAYMENT.getStatus());
        orderUpdateWrapper.set("create_time", LocalDateTime.now());
        orderUpdateWrapper.eq("id", orders.getId());
        orderUpdateWrapper.eq("status", OrderStatus.PAYMENT_FAIL.getStatus());
        boolean flag = ordersService.update(orderUpdateWrapper);
        if (!flag) {
            throw new ServiceException("订单再次发起支付失败，请核实订单状态");
        }
        Orders resultOrder = ordersService.getById(orders.getId());
        // 补充商家信息响应
        resultOrder.setMechantName(merchant.getName());
        resultOrder.setMechantLogo(merchant.getLogo());
        return AppletResult.success(resultOrder);
    }

    /**
     * 创建订单
     *
     * @return
     */
    @ApiOperation("创建订单接口")
    @PostMapping("/createOrder")
    public AppletResult createOrder(@RequestBody @Validated OrderCreateDTO orderCreateDTO) {
        // 获取商家信息
        Merchant merchant = merchantService.getById(orderCreateDTO.getMerchantId());
        if (!Optional.ofNullable(merchant).isPresent()) {
            throw new ServiceException("商家不存在");
        }
        // 获取商品信息
        Goods goods = goodsService.getById(orderCreateDTO.getGoodsId());
        if (!Optional.ofNullable(goods).isPresent()) {
            throw new ServiceException("商品不存在");
        }
        if (goods.getStatus() != GoodsStatus.SOLD_ON.getStatus()) {
            throw new ServiceException("商品未上架，不能购买");
        }
        if (goods.getQuantity() <= 0) {
            throw new ServiceException("商品已售完");
        }
        if (goods.getType().equals(GoodsType.SCATTERED_TICKET.getType())) {
            if (StringUtils.isBlank(orderCreateDTO.getUseDate())) {
                throw new ServiceException("购买散票商品，使用日期不能为空");
            }
        }
        // 获取活动信息
        Activity activity = null;
        if (goods.getType().equals(GoodsType.ACTIVITY_TICKET.getType())) {
            if (!Optional.ofNullable(orderCreateDTO.getActivityId()).isPresent()) {
                throw new ServiceException("购买活动商品，活动ID不能为空");
            }
            activity = activityService.getById(orderCreateDTO.getActivityId());
            if (!Optional.ofNullable(activity).isPresent()) {
                throw new ServiceException("活动不存在");
            }
        }

        // 获取当前登录用户
        User user = userService.getUserByRequest(request);
        // 订单号
        Long orderNo = IdWorker.getId();
        Orders orders = new Orders();
        orders.setOrderNo(orderNo);
        orders.setUserId(user.getId());
        orders.setMerchantId(orderCreateDTO.getMerchantId());
        orders.setGoodsId(goods.getId());
        orders.setGoodsType(goods.getType());
        orders.setGoodsName(goods.getName());
        orders.setGoodsPrice(goods.getPrice());
        orders.setQuantity(orderCreateDTO.getQuantity());
        // 计算订单总额
        BigDecimal orderAmount = CalculateUtils.orderAmount(goods.getPrice(), orderCreateDTO.getQuantity());
        orders.setOrderAmount(orderAmount);
        orders.setStatus(OrderStatus.NON_PAYMENT.getStatus());
        // 计算订单有效期
        if (goods.getType().equals(GoodsType.SCATTERED_TICKET.getType())) {
            // 散票
            String useDate = orderCreateDTO.getUseDate();
            String startTime = useDate + " " + merchant.getStartTime() + ":00";
            String endTime = useDate + " " + merchant.getEndTime() + ":00";
            LocalDateTime validStartTime = LocalDateTimeUtils.toLocalDateTime(startTime, "yyyy-MM-dd HH:mm:ss");
            LocalDateTime validEndTime = LocalDateTimeUtils.toLocalDateTime(endTime, "yyyy-MM-dd HH:mm:ss");
            if (validStartTime.isAfter(validEndTime)) {
                validEndTime = validEndTime.plusDays(1);
            }
            orders.setValidStartTime(validStartTime);
            orders.setValidEndTime(validEndTime);
        } else if (goods.getType().equals(GoodsType.ACTIVITY_TICKET.getType())) {
            // 活动票
            orders.setActivityId(activity.getId());
            orders.setActivityName(activity.getName());
            orders.setValidStartTime(activity.getStartTime());
            orders.setValidEndTime(activity.getEndTime());
        }
        orders.setCreateTime(LocalDateTime.now());
        orders.setUpdateTime(LocalDateTime.now());
        ordersService.save(orders);
        // 补充商家信息响应
        orders.setMechantName(merchant.getName());
        orders.setMechantLogo(merchant.getLogo());
        return AppletResult.success(orders);
    }

    /**
     * 支付订单
     *
     * @return
     */
    @ApiOperation("支付订单接口")
    @PostMapping("/payOrder")
    public AppletResult payOrder(@RequestBody @Validated OrderPayDTO orderPayDTO) throws Exception {
        // 获取订单信息
        Orders orders = ordersService.getById(orderPayDTO.getOrderId());
        if (!Optional.ofNullable(orders).isPresent()) {
            throw new ServiceException("订单不存在");
        }
        if (orders.getStatus() != OrderStatus.NON_PAYMENT.getStatus()) {
            throw new ServiceException("只有未支付状态的订单允许发起支付");
        }
        // 获取商家信息
        Merchant merchant = merchantService.getById(orders.getMerchantId());
        if (!Optional.ofNullable(merchant).isPresent()) {
            throw new ServiceException("商家不存在");
        }

        AppletWXPayConfig appletWXPayConfig = new AppletWXPayConfig();
        // 支付回调地址
        String notifyUrl = domainConfig.getAppletUrl() + "/order/notifyOrder";
        log.info("支付回调地址：{}", notifyUrl);
        WXPay wxPay = new WXPay(appletWXPayConfig, notifyUrl, false, false);

        Map<String, String> params = new HashMap<>();
        // 商品描述
        params.put("body", merchant.getName() + "-" + orders.getGoodsName());
        // 商户订单号
        params.put("out_trade_no", orders.getOrderNo().toString());
        // 设备
        params.put("device_info", "WEB");
        // 标价金额，订单总金额，单位为分
        int total_fee = CalculateUtils.yuanToFen(orders.getOrderAmount());
        params.put("total_fee", String.valueOf(total_fee));
        // 终端IP
        String spbill_create_ip = IpUtils.getIpAddr(request);
        params.put("spbill_create_ip", spbill_create_ip);
        // 交易类型，小程序取值如下：JSAPI
        params.put("trade_type", "JSAPI");
        // 获取当前登录用户
        User user = userService.getUserByRequest(request);
        // 用户标识
        params.put("openid", user.getOpenid());
        // 附加数据
        params.put("attach", orders.getId().toString());
        // 调用统一下单接口
        Map<String, String> results = wxPay.unifiedOrder(params);
        log.info("调用微信支付统一下单接口，响应数据：{}", JSON.toJSONString(results));
        // 获取通信标识
        String return_code = results.get("return_code");
        if (!return_code.equals(WXPayConstants.SUCCESS)) {
            throw new ServiceException("统一下单通信失败");
        }
        // 校验业务响应
        String result_code = results.get("result_code");
        if (!result_code.equals(WXPayConstants.SUCCESS)) {
            throw new ServiceException("统一下单业务失败");
        }
        // 响应给小程序的信息
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");
        resultMap.put("nonceStr", params.get("nonce_str"));
        resultMap.put("appId", appletConfig.getAppId());
        resultMap.put("package", "prepay_id=" + results.get("prepay_id"));
        resultMap.put("signType", WXPayConstants.MD5);
        // 签名
        String paySign = WXPayUtil.generateSignature(resultMap, appletWXPayConfig.getKey(), WXPayConstants.SignType.MD5);
        resultMap.put("paySign", paySign);
        resultMap.put("orderInfo", params.get("body"));
        return AppletResult.success(resultMap);
    }

    /**
     * 支付回调
     *
     * @return
     */
    @ApiOperation("支付回调接口")
    @PostMapping("/notifyOrder")
    public String notifyOrder() throws Exception {
        // 回调数据
        String resultXml = ServletUtils.requestStreamToString(request);
        Map<String, String> results = WXPayUtil.xmlToMap(resultXml);
        log.info("微信支付回调，响应数据：{}", JSON.toJSONString(results));

        // 获取通信标识
        String return_code = results.get("return_code");
        if (!return_code.equals("SUCCESS")) {
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

        // 校验业务响应
        String result_code = results.get("result_code");
        if (!result_code.equals("SUCCESS")) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("return_code", WXPayConstants.FAIL);
            responseMap.put("return_msg", "业务失败");
            String responseXml = WXPayUtil.mapToXml(responseMap);
            return responseXml;
        }

        // 商户订单号
        String out_trade_no = results.get("out_trade_no");
        // 订单总金额，单位为分
        String total_fee = results.get("total_fee");
        // 微信支付订单号
        String transaction_id = results.get("transaction_id");

        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.eq("order_no", Long.valueOf(out_trade_no));
        Orders orders = ordersService.getOne(ordersQueryWrapper);
        // 订单不存在
        if (!Optional.ofNullable(orders).isPresent()) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("return_code", WXPayConstants.FAIL);
            responseMap.put("return_msg", "订单不存在");
            String responseXml = WXPayUtil.mapToXml(responseMap);
            return responseXml;
        }

        // 校验订单金额
        if (!Integer.valueOf(total_fee).equals(CalculateUtils.yuanToFen(orders.getOrderAmount()))) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("return_code", WXPayConstants.FAIL);
            responseMap.put("return_msg", "订单金额不一致");
            String responseXml = WXPayUtil.mapToXml(responseMap);
            return responseXml;
        }

        // 如果订单状态是未完成直接响应成功
        if (orders.getStatus().equals(OrderStatus.PAYMENT.getStatus())) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("return_code", WXPayConstants.SUCCESS);
            responseMap.put("return_msg", "OK");
            String responseXml = WXPayUtil.mapToXml(responseMap);
            return responseXml;
        }

        // 生成核销码
        Long verificationCode = ordersService.generateVerificationCode();
        log.info("核销码：{}", verificationCode);
        // 生成核销二维码
        int width = 300;
        int height = 300;
        // 二维码内容
        String content = QRCodeCommand.VERIFICATION.getCommand() + "-" + orders.getOrderNo() + "-" + verificationCode;
        // 二维码保存路径
        String qrCodePath = RuoYiAppletConfig.getQRCodePath();
        String fileName = verificationCode + ".png";
        String filePath = qrCodePath + File.separator + fileName;
        // 生成二维码
        QRCodeUtils.encode(content, width, height, filePath);
        // 获取访问文件名
        String pathFileName = FileUploadUtils.getPathFileName(qrCodePath, fileName);
        log.info("核销码二维码访问文件名：{}", pathFileName);
        String url = domainConfig.getAdminUrl() + pathFileName;
        log.info("核销码二维码访问路径：{}", url);

        // 修改未支付的订单为已支付
        UpdateWrapper<Orders> orderUpdateWrapper = new UpdateWrapper<>();
        orderUpdateWrapper.set("verification_code", verificationCode);
        orderUpdateWrapper.set("verification_code_url", url);
        orderUpdateWrapper.set("status", OrderStatus.PAYMENT.getStatus());
        orderUpdateWrapper.set("trade_no", transaction_id);
        orderUpdateWrapper.set("update_time", LocalDateTime.now());
        orderUpdateWrapper.eq("id", orders.getId());
        orderUpdateWrapper.eq("status", OrderStatus.NON_PAYMENT.getStatus());
        boolean updateResult = ordersService.update(orderUpdateWrapper);
        if (updateResult) {
            // 扣减商品数量
            goodsService.subtractGoodsQuantity(orders.getGoodsId(), orders.getQuantity());
            // 增加商户余额
            merchantService.increaseBalance(orders.getMerchantId(), orders.getOrderAmount());
        }

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("return_code", WXPayConstants.SUCCESS);
        responseMap.put("return_msg", "OK");
        String responseXml = WXPayUtil.mapToXml(responseMap);
        return responseXml;
    }

    /**
     * 支付查单
     *
     * @return
     */
    @ApiOperation("支付查单接口")
    @PostMapping("/payQueryOrder")
    public AppletResult payQueryOrder(@RequestBody @Validated OrderPayQueryDTO orderPayQueryDTO) throws Exception {
        // 获取订单信息
        Orders orders = ordersService.getById(orderPayQueryDTO.getOrderId());
        if (!Optional.ofNullable(orders).isPresent()) {
            throw new ServiceException("订单不存在");
        }
        if (orders.getStatus() != OrderStatus.NON_PAYMENT.getStatus()) {
            throw new ServiceException("只有未支付状态的订单才允许查单");
        }

        // 返回交易状态
        String trade_state = ordersService.queryOrderHandle(orders);

        // 响应给小程序的信息
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("trade_state", trade_state);
        return AppletResult.success(resultMap);
    }

    /**
     * 删除订单接口
     *
     * @param orderDeleteDTO
     * @return
     */
    @ApiOperation("删除订单接口")
    @PostMapping("/delete")
    public AppletResult delete(@RequestBody @Validated OrderDeleteDTO orderDeleteDTO) {
        // 获取订单信息
        Orders orders = ordersService.getById(orderDeleteDTO.getOrderId());
        if (!Optional.ofNullable(orders).isPresent()) {
            throw new ServiceException("订单不存在");
        }
        if (orders.getStatus() != OrderStatus.PAYMENT_FAIL.getStatus()) {
            throw new ServiceException("只有支付失败状态的订单才允许删除");
        }
        boolean result = ordersService.removeById(orders.getId());
        return AppletResult.success(result);
    }

}