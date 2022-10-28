package com.ruoyi.applet.support.api.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信小程序 Code2Session 接口返回值 对象
 *
 * @author AlanLee
 * @date 2022-08-09
 */
@Data
public class Code2SessionResponse implements Serializable {

    private static final long serialVersionUID = -1843760732539950832L;

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    private String session_key;

    /**
     * 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回，详见 UnionID 机制说明。
     */
    private String unionid;

    /**
     * 错误码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

}