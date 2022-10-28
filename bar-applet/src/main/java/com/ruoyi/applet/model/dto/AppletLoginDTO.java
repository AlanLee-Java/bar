package com.ruoyi.applet.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 小程序登录请求参数
 *
 * @author AlanLee
 * @date 2022-08-26
 */
@Data
@ApiModel("小程序登录请求参数实体")
public class AppletLoginDTO implements Serializable {

    private static final long serialVersionUID = -2913924141471635269L;

    /**
     * 登录时获取的code
     */
    @NotBlank(message = "登录时获取的code不能为空")
    @ApiModelProperty("登录时获取的code")
    private String code;

    /**
     * 加密用户信息
     */
    @NotBlank(message = "加密用户信息不能为空")
    @ApiModelProperty("加密用户信息")
    private String encryptedData;

    /**
     * 加密偏移量
     */
    @NotBlank(message = "偏移量不能为空")
    @ApiModelProperty("加密偏移量")
    private String iv;

}