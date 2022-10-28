package com.ruoyi.common.enums;

/**
 * 退款状态枚举
 *
 * @author AlanLee
 * @date 2022-08-16
 */
public enum RefundStatus {

    /**
     * 提交申请
     */
    SUBMIT(0, "提交申请"),
    /**
     * 退款中
     */
    REFUND(1, "退款中"),
    /**
     * 退款成功
     */
    SUCCESS(2, "退款成功"),
    /**
     * 退款失败
     */
    FAIL(3, "退款失败");

    RefundStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * 退款状态
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

}