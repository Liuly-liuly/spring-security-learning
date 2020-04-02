package com.liuly.security.core.social.weixin.connection;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/5
 * @since JDK 1.8
 */
public class WeixinAccessGrant extends AccessGrant {

    private String openId;

//    public WeixinAccessGrant() {
//        super("");
//    }

    public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
