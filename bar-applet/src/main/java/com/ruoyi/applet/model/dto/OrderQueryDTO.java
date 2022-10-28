package com.ruoyi.applet.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 订单查询参数实体
 *
 * @author AlanLee
 * @date 2022-08-17
 */
@Data
@ApiModel("订单查询参数实体")
public class OrderQueryDTO implements Serializable {

    private static final long serialVersionUID = 7888560838466646952L;

    /**
     * 订单类型（查询不同状态订单使用）
     */
    @ApiModelProperty("订单类型：0全部，1待付款，2未完成，3已完成，4退款中/无效")
    @NotNull(message = "订单类型不能为空")
    private Integer orderType;

}