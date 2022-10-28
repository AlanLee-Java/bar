package com.ruoyi.applet.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商家查询参数实体
 *
 * @author AlanLee
 * @date 2022-08-11
 */
@Data
@ApiModel("商家查询参数实体")
public class MerchantQueryDTO implements Serializable {

    private static final long serialVersionUID = -5095656775937289821L;

    /**
     * 城市
     */
    @ApiModelProperty("城市")
    private String city;

    /**
     * 商家名称
     */
    @ApiModelProperty("商家名称")
    private String name;

}