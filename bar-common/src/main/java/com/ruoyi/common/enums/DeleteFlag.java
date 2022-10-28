package com.ruoyi.common.enums;

/**
 * 删除标记枚举
 *
 * @author AlanLee
 * @date 2022-08-31
 */
public enum DeleteFlag {

    /**
     * 未删除
     */
    NO_DELETE(0, "未删除"),
    /**
     * 已删除
     */
    YES_DELETE(1, "已删除");

    DeleteFlag(Integer deleteFlag, String desc) {
        this.deleteFlag = deleteFlag;
        this.desc = desc;
    }

    /**
     * 删除标记：0未删除，1已删除
     */
    private Integer deleteFlag;

    /**
     * 描述
     */
    private String desc;

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public String getDesc() {
        return desc;
    }

}