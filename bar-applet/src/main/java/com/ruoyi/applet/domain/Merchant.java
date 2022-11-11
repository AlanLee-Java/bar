package com.ruoyi.applet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 商家
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Merchant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商家余额
     */
    private BigDecimal balance;

    /**
     * 提现类型：1.微信，2支付宝，3银行卡
     */
    private Integer type;
    
    /**
     * 商户关键字：1酒吧，2夜店，3livehouse
     */
    private Integer typeKey;
    
    @TableField(exist = false)
    private String typeKeyName;

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
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    // 补充信息
    /**
     * 商家话题列表
     */
    @TableField(exist = false)
    private List<MerchantTopic> merchantTopics;

    /**
     * 散票列表
     */
    @TableField(exist = false)
    private List<Goods> scatteredTickets;

    /**
     * 套餐列表
     */
    @TableField(exist = false)
    private List<Goods> packages;

    /**
     * 商品详情
     */
    @TableField(exist = false)
    private Goods goods;

    /**
     * 活动列表
     */
    @TableField(exist = false)
    private List<Activity> activities;

}