package com.ruoyi.applet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bar_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 微信用户的唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    @JsonIgnore
    private String sessionKey;

    /**
     * 用户在微信开放平台的唯一标识符
     */
    private String unionid;

    /**
     * 微信昵称
     */
    private String wxName;

    /**
     * 微信头像
     */
    private String wxAvatar;

    /**
     * 盐加密
     */
    @JsonIgnore
    private String salt;

    /**
     * 访问令牌
     */
    @JsonIgnore
    private String accessToken;

    /**
     * 访问令牌有效时间
     */
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime accessTokenTime;

    /**
     * 最近一次登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}