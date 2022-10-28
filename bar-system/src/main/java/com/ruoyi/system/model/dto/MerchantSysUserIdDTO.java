package com.ruoyi.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 商家管理对象 merchant
 *
 * @author AlanLee
 * @date 2022-08-11
 */
@Data
public class MerchantSysUserIdDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    @NotNull(message = "商家ID不能为空")
    private Long id;

    /**
     * 商家管理员ID
     */
    @NotNull(message = "商家管理员ID不能为空")
    private Long sysUserId;

}