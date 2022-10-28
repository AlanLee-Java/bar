package com.ruoyi.applet.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 小程序登录响应实体
 *
 * @author AlanLee
 * @date 2022-08-09
 */
@ApiModel("小程序登录响应实体")
@Data
public class AppletLoginVO implements Serializable {

    private static final long serialVersionUID = 1430760148465299271L;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 微信昵称
     */
    @ApiModelProperty("微信昵称")
    private String wxName;

    /**
     * 微信头像
     */
    @ApiModelProperty("微信头像")
    private String wxAvatar;

    /**
     * 访问令牌
     */
    @ApiModelProperty("访问令牌")
    private String accessToken;

    /**
     * 访问令牌有效时间
     */
    @ApiModelProperty("访问令牌有效时间")
    private LocalDateTime accessTokenTime;

}