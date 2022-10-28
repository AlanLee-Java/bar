package com.ruoyi.applet.task;

import com.ruoyi.applet.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单相关定时任务
 *
 * @author AlanLee
 * @date 2022-08-17
 */
@Component
public class OrderTask {

    @Autowired
    private OrdersService ordersService;

    /**
     * 每30秒扫描一次支付超时订单，改为支付失败状态
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void payOvertime() {
        ordersService.payOvertimeHandle();
    }

}