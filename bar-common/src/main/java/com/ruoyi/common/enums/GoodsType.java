package com.ruoyi.common.enums;

/**
 * 商品类型枚举
 *
 * @author AlanLee
 * @date 2022-08-12
 */
public enum GoodsType {

    /**
     * 散票
     */
    SCATTERED_TICKET(1, "散票"),
    /**
     * 套餐
     */
    PACKAGE(2, "套餐"),
    /**
     * 活动票
     */
    ACTIVITY_TICKET(3, "活动票");

    GoodsType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    /**
     * 商品类型
     */
    private Integer type;

    /**
     * 类型说明
     */
    private String desc;

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

}