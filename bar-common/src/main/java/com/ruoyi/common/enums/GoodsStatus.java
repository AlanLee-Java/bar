package com.ruoyi.common.enums;

/**
 * 商品状态枚举
 *
 * @author AlanLee
 * @date 2022-08-14
 */
public enum GoodsStatus {

    /**
     * 待审核
     */
    AUDIT_PENDING(0, "待审核"),
    /**
     * 已上架
     */
    SOLD_ON(1, "已上架"),
    /**
     * 审核不通过
     */
    AUDIT_NO_PASS(2, "审核不通过"),
    /**
     * 已下架
     */
    SOLD_OUT(3, "已下架");

    GoodsStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * 商品状态：0待审核，1已上架，2审核不通过，3已下架
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