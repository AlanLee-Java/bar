package com.ruoyi.applet.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 订单退款参数实体
 *
 * @author AlanLee
 * @date 2022-08-16
 */
@Data
@ApiModel("订单退款参数实体")
public class RefundCreateDTO implements Serializable {

    private static final long serialVersionUID = -2958400423505498502L;

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空")
    @ApiModelProperty("订单ID")
    private Long orderId;

    /**
     * 退款原因
     */
    @NotBlank(message = "退款原因不能为空")
    @ApiModelProperty("退款原因")
    private String reason;

    /**
     * 退款详细原因
     */
    @ApiModelProperty("退款详细原因")
    private String reasonDetail;

}