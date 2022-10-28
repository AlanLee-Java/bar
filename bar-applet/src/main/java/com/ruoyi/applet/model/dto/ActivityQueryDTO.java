package com.ruoyi.applet.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 活动查询参数实体
 *
 * @author AlanLee
 * @date 2022-09-03
 */
@Data
@ApiModel("活动查询参数实体")
public class ActivityQueryDTO implements Serializable {

    private static final long serialVersionUID = 2023155647568496206L;

    /**
     * 活动名称
     */
    @ApiModelProperty("活动名称")
    private String name;

    /**
     * 城市
     */
    @ApiModelProperty("城市")
    private String city;

}