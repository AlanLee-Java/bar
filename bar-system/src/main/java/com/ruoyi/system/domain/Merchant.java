package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商家管理对象 merchant
 *
 * @author AlanLee
 * @date 2022-08-11
 */
@Data
public class Merchant extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商户角色id
     */
    public static final Long ROLE_MERCHANT = 3L;

    /**
     * 唯一主键
     */
    private Long id;

    /**
     * 商家余额
     */
    private BigDecimal balance;

    /**
     * 微信号
     */
    private String wechatId;

    /**
     * 支付宝号
     */
    private String alipayId;

    /**
     * 收账姓名
     */
    private String collectionName;

    /**
     * 收账卡号
     */
    private String collectionCardNo;

    /**
     * 收账银行
     */
    private String collectionBank;

    /**
     * 提现类型：1.微信，2支付宝，3银行卡
     */
    private Integer type;

    /**
     * 商家名称
     */
    private String name;

    /**
     * 商家logo
     */
    private String logo;

    /**
     * 商家城市
     */
    private String city;

    /**
     * 商家地址
     */
    private String address;

    /**
     * 商家描述
     */
    private String description;

    /**
     * 开始开放工作日
     */
    private Integer startDay;

    /**
     * 结束开放工作日
     */
    private Integer endDay;

    /**
     * 开始开放时间
     */
    private String startTime;

    /**
     * 结束开放时间
     */
    private String endTime;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 商家管理员ID
     */
    private Long sysUserId;

    /**
     * 商家管理员登陆账号
     */
    private String sysUserName;

}