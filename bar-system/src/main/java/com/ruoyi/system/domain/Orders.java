package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单对象 orders
 *
 * @author AlanLee
 * @date 2022-09-01
 */
public class Orders extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    private Long id;

    /**
     * 订单号
     */
    @Excel(name = "订单号")
    private Long orderNo;

    /**
     * 核销码
     */
    @Excel(name = "核销码")
    private Long verificationCode;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;
    
    /**
     * 用户名称
     */
    private String userName;

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
     * 活动ID
     */
    @Excel(name = "活动ID")
    private Long activityId;

    /**
     * 活动名称
     */
    @Excel(name = "活动名称")
    private String activityName;

    /**
     * 商品ID
     */
    @Excel(name = "商品ID")
    private Long goodsId;

    /**
     * 商品类型：1.散票，2.套餐，3.活动票
     */
    @Excel(name = "商品类型：1.散票，2.套餐，3.活动票")
    private Integer goodsType;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String goodsName;

    /**
     * 商品价格
     */
    @Excel(name = "商品价格")
    private BigDecimal goodsPrice;

    /**
     * 购买数量
     */
    @Excel(name = "购买数量")
    private Integer quantity;

    /**
     * 订单总额
     */
    @Excel(name = "订单总额")
    private BigDecimal orderAmount;

    /**
     * 订单状态：0待付款，1未完成，2已完成，3退款中，4已退款，5退款失败，6支付失败，7已失效
     */
    @Excel(name = "订单状态：0待付款，1未完成，2已完成，3退款中，4已退款，5退款失败，6支付失败，7已失效")
    private Integer status;

    /**
     * 支付渠道：1微信支付
     */
    @Excel(name = "支付渠道：1微信支付")
    private Integer payChannel;

    /**
     * 支付单号
     */
    @Excel(name = "支付单号")
    private String tradeNo;

    /**
     * 有效开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validStartTime;

    /**
     * 有效结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validEndTime;

    /**
     * 消费时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "消费时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date consumeTime;

    /**
     * 删除标记：0未删除，1已删除
     */
    private Integer deleteFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setVerificationCode(Long verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Long getVerificationCode() {
        return verificationCode;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }

    public Integer getPayChannel() {
        return payChannel;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setValidStartTime(Date validStartTime) {
        this.validStartTime = validStartTime;
    }

    public Date getValidStartTime() {
        return validStartTime;
    }

    public void setValidEndTime(Date validEndTime) {
        this.validEndTime = validEndTime;
    }

    public Date getValidEndTime() {
        return validEndTime;
    }

    public void setConsumeTime(Date consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Date getConsumeTime() {
        return consumeTime;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderNo", getOrderNo())
                .append("verificationCode", getVerificationCode())
                .append("userId", getUserId())
                .append("merchantId", getMerchantId())
                .append("activityId", getActivityId())
                .append("activityName", getActivityName())
                .append("goodsId", getGoodsId())
                .append("goodsType", getGoodsType())
                .append("goodsName", getGoodsName())
                .append("goodsPrice", getGoodsPrice())
                .append("quantity", getQuantity())
                .append("orderAmount", getOrderAmount())
                .append("status", getStatus())
                .append("payChannel", getPayChannel())
                .append("tradeNo", getTradeNo())
                .append("validStartTime", getValidStartTime())
                .append("validEndTime", getValidEndTime())
                .append("consumeTime", getConsumeTime())
                .append("deleteFlag", getDeleteFlag())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
