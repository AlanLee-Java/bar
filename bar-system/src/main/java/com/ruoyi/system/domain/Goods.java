package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 商品对象 goods
 *
 * @author AlanLee
 * @date 2022-08-29
 */
public class Goods extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    //商品状态：0待审核，1已上架，2审核不通过，3已下架
    /** 0待审核  */
    public static final int STATUS_DAI_CHECK = 0;
    /** 1已上架  */
    public static final int STATUS_CHECK_SUCCESS = 1;
    /** 2审核不通过  */
    public static final int STATUS_CHECK_FAIL = 2;
    /** 3已下架  */
    public static final int STATUS_XIAJIA = 3;

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
     * 商品类型：1.散票，2.套餐，3.活动票
     */
    @Excel(name = "商品类型：1.散票，2.套餐，3.活动票")
    private Integer type;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String name;

    /**
     * 商品描述
     */
    @Excel(name = "商品描述")
    private String description;

    /**
     * 商品价格
     */
    @Excel(name = "商品价格")
    private BigDecimal price;

    /**
     * 商品数量
     */
    @Excel(name = "商品数量")
    private Integer quantity;

    /**
     * 商品状态：0待审核，1已上架，2审核不通过，3已下架
     */
    @Excel(name = "商品状态：0待审核，1已上架，2审核不通过，3已下架")
    private Integer status;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("merchantId", getMerchantId())
                .append("type", getType())
                .append("name", getName())
                .append("description", getDescription())
                .append("price", getPrice())
                .append("quantity", getQuantity())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
