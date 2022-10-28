package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品退款规则对象 goods_refund_rule
 *
 * @author AlanLee
 * @date 2022-08-29
 */
public class GoodsRefundRule extends BaseEntity {
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
     * 商品ID
     */
    @Excel(name = "商品ID")
    private Long goodsId;

    /**
     * 退款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date refundTime;

    /**
     * 条件类型：1前，不包括规则时间，2后，包括规则时间
     */
    @Excel(name = "条件类型：1前，不包括规则时间，2后，包括规则时间")
    private Integer conditionType;

    /**
     * 是否允许退款：0不允许，1允许
     */
    @Excel(name = "是否允许退款：0不允许，1允许")
    private Integer isAllow;

    /**
     * 退款比例
     */
    @Excel(name = "退款比例")
    private BigDecimal refundRate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setConditionType(Integer conditionType) {
        this.conditionType = conditionType;
    }

    public Integer getConditionType() {
        return conditionType;
    }

    public void setIsAllow(Integer isAllow) {
        this.isAllow = isAllow;
    }

    public Integer getIsAllow() {
        return isAllow;
    }

    public void setRefundRate(BigDecimal refundRate) {
        this.refundRate = refundRate;
    }

    public BigDecimal getRefundRate() {
        return refundRate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("goodsId", getGoodsId())
                .append("refundTime", getRefundTime())
                .append("conditionType", getConditionType())
                .append("isAllow", getIsAllow())
                .append("refundRate", getRefundRate())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
