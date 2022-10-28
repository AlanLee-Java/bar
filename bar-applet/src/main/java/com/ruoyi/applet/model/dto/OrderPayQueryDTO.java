package com.ruoyi.applet.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 订单支付查询参数实体
 *
 * @author AlanLee
 * @date 2022-08-31
 */
@Data
@ApiModel("订单支付查单参数实体")
public class OrderPayQueryDTO implements Serializable {

    private static final long serialVersionUID = 7554445994665168338L;

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空")
    @ApiModelProperty("订单ID")
    private Long orderId;

}