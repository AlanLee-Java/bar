package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 退款单对象 refund
 *
 * @author AlanLee
 * @date 2022-09-22
 */
public class Refund extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    private Long id;

    /**
     * 退款号
     */
    @Excel(name = "退款号")
    private Long refundNo;

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
     * 商户ID
     */
    @Excel(name = "商户ID")
    private Long merchantId;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 订单ID
     */
    @Excel(name = "订单ID")
    private Long orderId;
    
    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 退款金额
     */
    @Excel(name = "退款金额")
    private BigDecimal refundAmount;

    /**
     * 退款原因
     */
    @Excel(name = "退款原因")
    private String reason;

    /**
     * 退款详细原因
     */
    @Excel(name = "退款详细原因")
    private String reasonDetail;

    /**
     * 退款状态：0提交申请，1退款中，2退款成功，3退款失败
     */
    @Excel(name = "退款状态：0提交申请，1退款中，2退款成功，3退款失败")
    private Integer status;

    /**
     * 退款失败原因
     */
    @Excel(name = "退款失败原因")
    private String failReason;

    /**
     * 退款单号
     */
    @Excel(name = "退款单号")
    private String tradeNo;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setRefundNo(Long refundNo) {
        this.refundNo = refundNo;
    }

    public Long getRefundNo() {
        return refundNo;
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

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReasonDetail(String reasonDetail) {
        this.reasonDetail = reasonDetail;
    }

    public String getReasonDetail() {
        return reasonDetail;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("refundNo", getRefundNo())
                .append("userId", getUserId())
                .append("merchantId", getMerchantId())
                .append("orderId", getOrderId())
                .append("refundAmount", getRefundAmount())
                .append("reason", getReason())
                .append("reasonDetail", getReasonDetail())
                .append("status", getStatus())
                .append("failReason", getFailReason())
                .append("tradeNo", getTradeNo())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
