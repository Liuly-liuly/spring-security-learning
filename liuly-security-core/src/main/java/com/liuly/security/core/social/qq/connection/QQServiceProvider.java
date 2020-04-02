package com.liuly.security.core.social.qq.connection;

import com.liuly.security.core.social.qq.api.QQGetUserInfo;
import com.liuly.security.core.social.qq.api.QQGetUserInfoImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/4
 * @since JDK 1.8
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQGetUserInfo> {

    private String appId;
    //导向认证服务器的地址授权并返回令牌
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    //通过授权码去取accessToken
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
    //提供OAuth2Operation,导向认证服务器得到用户授权码，再去取accessToken 传向api得到用户信息
    public QQServiceProvider(String appId, String appSecret) {
        //原始的template 在解析数据格式上回出现异常，需要自定义转换
        //super(new OAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        super(new QQOAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQGetUserInfo getApi(String accessToken) {
        return new QQGetUserInfoImpl(accessToken,appId);
    }
}
