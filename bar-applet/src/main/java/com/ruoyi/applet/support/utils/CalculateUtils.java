package com.ruoyi.applet.support.utils;

import java.math.BigDecimal;

/**
 * 计算工具类
 *
 * @author AlanLee
 * @date 2022-08-13
 */
public class CalculateUtils {

    /**
     * 计算订单总额
     *
     * @param goodsPrice
     * @param quantity
     * @return
     */
    public static BigDecimal orderAmount(BigDecimal goodsPrice, Integer quantity) {
        return goodsPrice.multiply(new BigDecimal(quantity));
    }

    /**
     * 计算退款金额
     *
     * @param orderAmount
     * @param refundRate
     * @return
     */
    public static BigDecimal refundAmount(BigDecimal orderAmount, BigDecimal refundRate) {
        return orderAmount.multiply(refundRate).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 元转分
     *
     * @param yuan
     */
    public static int yuanToFen(BigDecimal yuan) {
        return yuan.multiply(new BigDecimal(100)).intValue();
    }

}