package com.ruoyi.applet.constant;

/**
 * 小程序常量接口
 *
 * @author AlanLee
 * @date 2022-08-13
 */
public interface AppletConstant {

    /**
     * 授权头
     */
    String AUTHORIZATION_HEADER = "Authorization";

    /**
     * 小程序获取openid、session_key、unionid请求地址
     */
    String CODE_2_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";

}