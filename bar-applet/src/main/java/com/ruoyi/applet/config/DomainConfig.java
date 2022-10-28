package com.ruoyi.applet.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 访问地址配置
 *
 * @author AlanLee
 * @date 2022-09-08
 */
@Configuration
@Data
public class DomainConfig {

    /**
     * 小程序端访问地址
     */
    @Value("${domain.appletUrl}")
    private String appletUrl;

    /**
     * 后台管理访问地址
     */
    @Value("${domain.adminUrl}")
    private String adminUrl;

}