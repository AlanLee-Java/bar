package com.ruoyi.applet.support.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.applet.config.AppletConfig;
import com.ruoyi.applet.constant.AppletConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 小程序API调用
 *
 * @author AlanLee
 * @date 2022-08-09
 */
@Component
@Slf4j
public class AppletApi {

    @Autowired
    private AppletConfig appletConfig;

    /**
     * 获取openid、session_key、unionid
     *
     * @param code
     * @return
     */
    public JSONObject code2Session(String code) {
        String result = null;
        try {
            String url = AppletConstant.CODE_2_SESSION_URL;
            url += "?appid=" + appletConfig.getAppId();
            url += "&secret=" + appletConfig.getAppSecret();
            url += "&js_code=" + code;
            url += "&grant_type=authorization_code";
            url += "&connect_redirect=1";
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            // GET方式
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = null;
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(false)
                    .build();
            // 将上面的配置信息 运用到这个Get请求里
            httpget.setConfig(requestConfig);
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpget);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity);
                log.info("调用code2Session响应数据：{}", result);
            }
            // 释放资源
            if (httpClient != null) {
                httpClient.close();
            }
            if (response != null) {
                response.close();
            }
            JSONObject jsonObject = JSON.parseObject(result);
            return jsonObject;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}