package com.ruoyi.applet.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 订单创建参数实体
 *
 * @author AlanLee
 * @date 2022-08-13
 */
@Data
@ApiModel("订单创建参数实体")
public class OrderCreateDTO implements Serializable {

    private static final long serialVersionUID = -8528274393294117065L;

    /**
     * 商家ID
     */
    @NotNull(message = "商家ID不能为空")
    @ApiModelProperty("商家ID")
    private Long merchantId;

    /**
     * 活动ID
     */
    @ApiModelProperty("活动ID")
    private Long activityId;

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空")
    @ApiModelProperty("商品ID")
    private Long goodsId;

    /**
     * 购买数量
     */
    @NotNull(message = "购买数量不能为空")
    @ApiModelProperty(value = "购买数量", example = "1")
    private Integer quantity;

    /**
     * 使用日期
     */
    @ApiModelProperty(value = "使用日期", example = "2022-08-31")
    private String useDate;

}