package com.ruoyi.common.enums;

/**
 * 问题反馈状态枚举
 *
 * @author AlanLee
 * @date 2022-09-02
 */
public enum FeedbackStatus {

    /**
     * 待处理
     */
    PENDING(0, "待处理"),
    /**
     * 已处理
     */
    PROCESSED(1, "已处理");

    FeedbackStatus(Integer status, String desc) {
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

}