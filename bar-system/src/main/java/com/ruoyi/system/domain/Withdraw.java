package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现申请对象 withdraw
 *
 * @author AlanLee
 * @date 2022-10-11
 */
@Data
public class Withdraw extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    private Long id;

    /**
     * 商家ID
     */
    @Excel(name = "商家ID")
    private Long merchantId;
    
    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 微信号
     */
    @Excel(name = "微信号")
    private String wechatId;

    /**
     * 支付宝号
     */
    @Excel(name = "支付宝号")
    private String alipayId;

    /**
     * 收账姓名
     */
    @Excel(name = "收账姓名")
    private String collectionName;

    /**
     * 收账卡号
     */
    @Excel(name = "收账卡号")
    private String collectionCardNo;

    /**
     * 收账银行
     */
    @Excel(name = "收账银行")
    private String collectionBank;

    /**
     * 提现类型：1.微信，2支付宝，3银行卡
     */
    @Excel(name = "提现类型：1.微信，2支付宝，3银行卡")
    private Integer withdrawType;

    /**
     * 提现金额
     */
    @Excel(name = "提现金额")
    private BigDecimal amount;

    /**
     * 状态：0申请中，1已提现，2已拒绝
     */
    @Excel(name = "状态：0申请中，1已提现，2已拒绝")
    private Integer status;

    /**
     * 提现凭证图片
     */
    private String proof;

    /**
     * 处理人
     */
    @Excel(name = "处理人")
    private String handleBy;

    /**
     * 处理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date handleTime;

    /**
     * 处理备注
     */
    @Excel(name = "处理备注")
    private String handleRemark;

}