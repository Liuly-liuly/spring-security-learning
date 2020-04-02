package com.liuly.security.core.properties;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/12
 * @since JDK 1.8
 */
public class OAuth2Properties {

    private OAuth2ClientProperties [] clients={};

    private String jwrSignKey = "liulyandpeimin";

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getJwrSignKey() {
        return jwrSignKey;
    }

    public void setJwrSignKey(String jwrSignKey) {
        this.jwrSignKey = jwrSignKey;
    }
}
