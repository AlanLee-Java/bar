package com.ruoyi.applet.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class RefundApplyDTO implements Serializable {

    private static final long serialVersionUID = 2631033176580074314L;

    /**
     * 退款ID
     */
    @NotNull(message = "退款ID不能为空")
    @ApiModelProperty("退款ID")
    private Long refundId;

}