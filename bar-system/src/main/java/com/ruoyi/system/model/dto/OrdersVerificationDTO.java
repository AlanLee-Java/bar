package com.ruoyi.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 订单核销参数实体
 *
 * @author AlanLee
 * @date 2022-10-11
 */
@Data
public class OrdersVerificationDTO implements Serializable {

    private static final long serialVersionUID = 4465953508753063030L;

    /**
     * 核销码
     */
    @NotNull(message = "核销码不能为空")
    private Long verificationCode;

}