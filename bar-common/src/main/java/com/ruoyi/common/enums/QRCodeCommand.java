package com.ruoyi.common.enums;

/**
 * 二维码指令枚举
 *
 * @author AlanLee
 * @date 2022-09-07
 */
public enum QRCodeCommand {

    /**
     * 核销指令
     */
    VERIFICATION("verification", "核销");

    QRCodeCommand(String command, String desc) {
        this.command = command;
        this.desc = desc;
    }

    /**
     * 指令
     */
    private String command;

    /**
     * 描述
     */
    private String desc;

    public String getCommand() {
        return command;
    }

    public String getDesc() {
        return desc;
    }

}