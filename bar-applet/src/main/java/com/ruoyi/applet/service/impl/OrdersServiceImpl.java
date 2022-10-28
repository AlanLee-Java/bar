package com.ruoyi.applet.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.applet.config.DomainConfig;
import com.ruoyi.applet.config.RuoYiAppletConfig;
import com.ruoyi.applet.domain.Orders;
import com.ruoyi.applet.mapper.OrdersMapper;
import com.ruoyi.applet.service.GoodsService;
import com.ruoyi.applet.service.MerchantService;
import com.ruoyi.applet.service.OrdersService;
import com.ruoyi.applet.support.utils.FileUploadUtils;
import com.ruoyi.applet.support.utils.QRCodeUtils;
import com.ruoyi.applet.support.wxpay.AppletWXPayConfig;
import com.ruoyi.applet.support.wxpay.WXPay;
import com.ruoyi.applet.support.wxpay.WXPayConstants;
import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.common.enums.QRCodeCommand;
import com.ruoyi.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-13
 */
@Service
@Slf4j
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private DomainConfig domainConfig;

    /**
     * 查单处理，返回交易状态
     *
     * @param orders
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String queryOrderHandle(Orders orders) throws Exception {
        AppletWXPayConfig appletWXPayConfig = new AppletWXPayConfig();
        WXPay wxPay = new WXPay(appletWXPayConfig, null, false, false);

        Map<String, String> params = new HashMap<>();
        // 商户订单号
        params.put("out_trade_no", orders.getOrderNo().toString());

        // 调用查询订单接口
        Map<String, String> results = wxPay.orderQuery(params);
        log.info("调用微信支付查单接口，响应数据：", JSON.toJSONString(results));
        // 获取通信标识
        String return_code = results.get("return_code");
        if (!return_code.equals(WXPayConstants.SUCCESS)) {
            throw new ServiceException("查询订单通信失败");
        }
        // 校验业务响应
        String result_code = results.get("result_code");
        if (!result_code.equals(WXPayConstants.SUCCESS)) {
            throw new ServiceException("查询订单业务失败");
        }
        // 交易状态
        String trade_state = results.get("trade_state");
        if (trade_state.equals("SUCCESS")) {
            // 获取支付订单号
            String transaction_id = results.get("transaction_id");

            // 生成核销码
            Long verificationCode = this.generateVerificationCode();
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
            String url = domainConfig.getAdminUrl() + pathFileName;

            // 修改未支付的订单为已支付
            UpdateWrapper<Orders> orderUpdateWrapper = new UpdateWrapper<>();
            orderUpdateWrapper.set("verification_code", verificationCode);
            orderUpdateWrapper.set("verification_code_url", url);
            orderUpdateWrapper.set("status", OrderStatus.PAYMENT.getStatus());
            orderUpdateWrapper.set("trade_no", transaction_id);
            orderUpdateWrapper.set("update_time", LocalDateTime.now());
            orderUpdateWrapper.eq("id", orders.getId());
            orderUpdateWrapper.eq("status", OrderStatus.NON_PAYMENT.getStatus());
            boolean updateResult = this.update(orderUpdateWrapper);
            if (updateResult) {
                // 扣减商品数量
                goodsService.subtractGoodsQuantity(orders.getGoodsId(), orders.getQuantity());
                // 增加商家余额
                merchantService.increaseBalance(orders.getMerchantId(), orders.getOrderAmount());
            }
        }
        return trade_state;
    }

    /**
     * 支付超时处理
     */
    @Override
    public void payOvertimeHandle() {
        // 计算15分钟前的时间
        LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(15);
        UpdateWrapper<Orders> orderUpdateWrapper = new UpdateWrapper<>();
        orderUpdateWrapper.set("status", OrderStatus.PAYMENT_FAIL.getStatus());
        orderUpdateWrapper.eq("status", OrderStatus.NON_PAYMENT.getStatus());
        orderUpdateWrapper.le("create_time", localDateTime);
        this.update(orderUpdateWrapper);
    }

    /**
     * 生成核销码
     *
     * @return
     */
    @Override
    public Long generateVerificationCode() {
        Long verificationCode = null;
        do {
            if (Optional.ofNullable(verificationCode).isPresent()) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            verificationCode = Long.valueOf(System.currentTimeMillis() / 1000 + RandomUtil.randomNumbers(2));
        } while (this.count(new QueryWrapper<Orders>().eq("verification_code", verificationCode)) > 0);
        return verificationCode;
    }

}