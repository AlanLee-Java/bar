package com.ruoyi.applet.config;

import com.ruoyi.applet.support.interceptor.AppletLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * web配置类
 *
 * @author AlanLee
 * @date 2022-08-10
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AppletLoginInterceptor appletLoginInterceptor;

    /**
     * 自定义拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> appletLoginExcludePaths = new ArrayList<>();
        // 排除swagger相关路径
        appletLoginExcludePaths.add("/swagger-ui/**");
        appletLoginExcludePaths.add("/swagger-resources/**");
        appletLoginExcludePaths.add("/v3/**");
        // 排除用户小程序登录接口
        appletLoginExcludePaths.add("/user/appletLogin");
        // 排除文章相关接口
        appletLoginExcludePaths.add("/article/**");
        // 排除小程序支付回调接口
        appletLoginExcludePaths.add("/order/notifyOrder");
        // 排除小程序退款回调接口
        appletLoginExcludePaths.add("/refund/notifyRefund");
        // 排除商家查询相关接口
        appletLoginExcludePaths.add("/merchant/**");
        // 排除活动查询相关接口
        appletLoginExcludePaths.add("/activity/**");
        // 排除字典数据相关接口
        appletLoginExcludePaths.add("/sysDictData/**");
        registry.addInterceptor(appletLoginInterceptor).addPathPatterns("/**")
                .excludePathPatterns(appletLoginExcludePaths);
    }

}