package com.liuly.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/15
 * @since JDK 1.8
 */
@Component
public class CoreAuthorizeConfigManager implements  AuthorizeConfigManager {

    @Autowired
    List<AuthorizeConfigProvider> providers;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        for(AuthorizeConfigProvider provider:providers) {
            provider.config(config);
        }
        config.anyRequest().authenticated(); //涉及demo中权限的问题，启动demo 这个需要关闭
    }
}
