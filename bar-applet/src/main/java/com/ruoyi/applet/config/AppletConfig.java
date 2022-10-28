package com.ruoyi.applet.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 微信小程序配置
 *
 * @author AlanLee
 * @date 2022-08-09
 */
@Configuration
@Data
public class AppletConfig {

    /**
     * 小程序应用ID
     */
    @Value("${applet.appId}")
    private String appId;

    /**
     * 小程序应用密钥
     */
    @Value("${applet.appSecret}")
    private String appSecret;

    /**
     * 小程序会话超时天数
     */
    @Value("${applet.sessionExpireDay}")
    private Long sessionExpireDay;

}