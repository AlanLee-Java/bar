package com.ruoyi.applet.support.wxpay;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 小程序支付配置
 *
 * @author AlanLee
 * @date 2022-08-23
 */
public class AppletWXPayConfig extends WXPayConfig {

    private byte[] certData;

    public AppletWXPayConfig() throws Exception {
        String certPath = "/opt/bar/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    @Override
    public String getAppID() {
        return "wx06193bf2e5138b1d";
    }

    @Override
    public String getMchID() {
        return "1630068512";
    }

    @Override
    public String getKey() {
        return "566af6e6812aasongge2fdb4f4d37989";
    }

    @Override
    InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo("api.mch.weixin.qq.com", true);
            }
        };
    }

}