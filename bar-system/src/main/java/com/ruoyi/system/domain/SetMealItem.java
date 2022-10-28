package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 套餐项目对象 set_meal_item
 *
 * @author AlanLee
 * @date 2022-09-01
 */
public class SetMealItem extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    private Long id;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 套餐ID
     */
    @Excel(name = "套餐ID")
    private Long setMealId;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String name;

    /**
     * 项目价格
     */
    @Excel(name = "项目价格")
    private BigDecimal price;

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

    public void setSetMealId(Long setMealId) {
        this.setMealId = setMealId;
    }

    public Long getSetMealId() {
        return setMealId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("setMealId", getSetMealId())
                .append("name", getName())
                .append("price", getPrice())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
