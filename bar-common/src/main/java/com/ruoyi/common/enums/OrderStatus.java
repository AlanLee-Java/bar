package com.ruoyi.common.enums;

/**
 * 订单状态枚举
 *
 * @author AlanLee
 * @date 2022-08-12
 */
public enum OrderStatus {

    /**
     * 未支付
     */
    NON_PAYMENT(0, "待付款"),
    /**
     * 未完成
     */
    PAYMENT(1, "未完成"),
    /**
     * 已完成
     */
    DONE(2, "已完成"),
    /**
     * 退款中
     */
    REFUND(3, "退款中"),
    /**
     * 已退款
     */
    REFUNDED(4, "已退款"),
    /**
     * 退款失败
     */
    REFUND_FAIL(5, "退款失败"),
    /**
     * 支付失败
     */
    PAYMENT_FAIL(6, "支付失败"),
    /**
     * 已失效
     */
    INVALID(7, "已失效");

    OrderStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 状态说明
     */
    private String desc;

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据状态获取描述
     *
     * @param status
     * @return
     */
    public static String getDescByStatus(int status) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.getStatus() == status) {
                return orderStatus.getDesc();
            }
        }
        return null;
    }

}