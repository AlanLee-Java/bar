package com.ruoyi.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.applet.domain.Orders;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-13
 */
public interface OrdersService extends IService<Orders> {

    /**
     * 查单处理，返回交易状态
     *
     * @param order
     */
    String queryOrderHandle(Orders order) throws Exception;

    /**
     * 支付超时处理
     */
    void payOvertimeHandle();

    /**
     * 生成核销码
     *
     * @return
     */
    Long generateVerificationCode();

}