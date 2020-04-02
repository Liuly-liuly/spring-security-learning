package com.liuly.security.core.social.qq.connection;

import com.liuly.security.core.social.qq.api.QQGetUserInfo;
import org.springframework.social.ServiceProvider;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/4
 * @since JDK 1.8
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQGetUserInfo> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQApiAdapter());
    }

}
