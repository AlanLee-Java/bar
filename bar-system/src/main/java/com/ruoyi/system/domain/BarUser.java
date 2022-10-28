package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * bar用户对象 bar_user
 * 
 * @author AlanLee
 * @date 2022-09-22
 */
public class BarUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一主键 */
    private Long id;

    /** 微信用户的唯一标识 */
    @Excel(name = "微信用户的唯一标识")
    private String openid;

    /** 会话密钥 */
    private String sessionKey;

    /** 用户在微信开放平台的唯一标识符 */
    @Excel(name = "用户在微信开放平台的唯一标识符")
    private String unionid;

    /** 微信昵称 */
    @Excel(name = "微信昵称")
    private String wxName;

    /** 微信头像 */
    @Excel(name = "微信头像")
    private String wxAvatar;

    /** 盐加密 */
    private String salt;

    /** 访问令牌 */
    private String accessToken;

    /** 访问令牌有效时间 */
    private Date accessTokenTime;

    /** 最近一次登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近一次登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loginTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setSessionKey(String sessionKey) 
    {
        this.sessionKey = sessionKey;
    }

    public String getSessionKey() 
    {
        return sessionKey;
    }
    public void setUnionid(String unionid) 
    {
        this.unionid = unionid;
    }

    public String getUnionid() 
    {
        return unionid;
    }
    public void setWxName(String wxName) 
    {
        this.wxName = wxName;
    }

    public String getWxName() 
    {
        return wxName;
    }
    public void setWxAvatar(String wxAvatar) 
    {
        this.wxAvatar = wxAvatar;
    }

    public String getWxAvatar() 
    {
        return wxAvatar;
    }
    public void setSalt(String salt) 
    {
        this.salt = salt;
    }

    public String getSalt() 
    {
        return salt;
    }
    public void setAccessToken(String accessToken) 
    {
        this.accessToken = accessToken;
    }

    public String getAccessToken() 
    {
        return accessToken;
    }
    public void setAccessTokenTime(Date accessTokenTime) 
    {
        this.accessTokenTime = accessTokenTime;
    }

    public Date getAccessTokenTime() 
    {
        return accessTokenTime;
    }
    public void setLoginTime(Date loginTime) 
    {
        this.loginTime = loginTime;
    }

    public Date getLoginTime() 
    {
        return loginTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openid", getOpenid())
            .append("sessionKey", getSessionKey())
            .append("unionid", getUnionid())
            .append("wxName", getWxName())
            .append("wxAvatar", getWxAvatar())
            .append("salt", getSalt())
            .append("accessToken", getAccessToken())
            .append("accessTokenTime", getAccessTokenTime())
            .append("loginTime", getLoginTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
