package com.ruoyi.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.applet.domain.Orders;
import com.ruoyi.applet.domain.Refund;

import java.math.BigDecimal;

/**
 * <p>
 * 退款 服务类
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-16
 */
public interface RefundService extends IService<Refund> {

    /**
     * 获取订单退款金额
     *
     * @param orders
     * @return
     */
    BigDecimal refundAmount(Orders orders);

    /**
     * 申请退款异步处理
     *
     * @param refund
     */
    void applyRefund(Refund refund, String notifyUrl) throws Exception;

}