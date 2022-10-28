package com.ruoyi.applet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置
 *
 * @author AlanLee
 * @date 2022-08-09
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        // 10秒连接超时
        simpleClientHttpRequestFactory.setConnectTimeout(1000 * 10);
        // 60秒读取超时
        simpleClientHttpRequestFactory.setReadTimeout(1000 * 60);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(simpleClientHttpRequestFactory);
        return restTemplate;
    }

}