package com.ruoyi.applet.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 订单支付参数实体
 *
 * @author AlanLee
 * @date 2022-08-14
 */
@Data
@ApiModel("订单支付参数实体")
public class OrderPayDTO implements Serializable {

    private static final long serialVersionUID = -2599337270988327589L;

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空")
    @ApiModelProperty("订单ID")
    private Long orderId;

    /**
     * 支付渠道：1微信支付
     */
    @NotNull(message = "支付渠道不能为空")
    @Range(min = 1, max = 1, message = "支付渠道错误")
    @ApiModelProperty("支付渠道")
    private Integer payChannel;

}