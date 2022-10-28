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
 * 订单
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    private Long orderNo;

    /**
     * 核销码
     */
    private Long verificationCode;

    /**
     * 核销码访问地址
     */
    private String verificationCodeUrl;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品类型：1.散票，2.套餐，3.活动票
     */
    private Integer goodsType;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 订单总额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态：0待付款，1未完成，2已完成，3退款中，4已退款，5退款失败，6支付失败，7已失效
     */
    private Integer status;

    /**
     * 支付渠道：1微信支付
     */
    private Integer payChannel;

    /**
     * 支付单号
     */
    private String tradeNo;

    /**
     * 有效开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime validStartTime;

    /**
     * 有效结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime validEndTime;

    /**
     * 消费时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime consumeTime;

    /**
     * 删除标记：0未删除，1已删除
     */
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    // 补充信息
    /**
     * 状态描述
     */
    @TableField(exist = false)
    private String statusDesc;

    /**
     * 商家名称
     */
    @TableField(exist = false)
    private String mechantName;

    /**
     * 商家logo
     */
    @TableField(exist = false)
    private String mechantLogo;

    /**
     * 商家联系方式
     */
    @TableField(exist = false)
    private String mechantContact;

    /**
     * 商家话题列表
     */
    @TableField(exist = false)
    private List<MerchantTopic> merchantTopics;

    /**
     * 退款金额
     */
    @TableField(exist = false)
    private BigDecimal refundAmount;

    /**
     * 退款原因
     */
    @TableField(exist = false)
    private String refundReason;

    /**
     * 退款原因详情
     */
    @TableField(exist = false)
    private String refundReasonDetail;

    /**
     * 退款状态：0提交申请，1退款中，2退款成功，3退款失败
     */
    @TableField(exist = false)
    private Integer refundStatus;

}