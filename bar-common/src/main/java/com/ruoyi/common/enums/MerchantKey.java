package com.ruoyi.common.enums;

/**
 * 商户类型枚举
 *
 * @author AlanLee
 * @date 2022-08-12
 */
public enum MerchantKey {

    /**
     * 酒店
     */
    JIUDIAN(1, "酒吧", "酒吧"),
    /**
     * 夜店
     */
    YEDIAN(2, "夜店", "夜店"),
    /**
     * livehouse
     */
    LIVE_HOUSE(3,"livehouse","Live House");

    MerchantKey(Integer type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    /**
     * 商户类型
     */
    private Integer type;

    private String name;
    /**
     * 类型说明
     */
    private String desc;

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

	public String getName() {
		return name;
	}


}