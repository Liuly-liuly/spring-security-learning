package com.liuly.security.core.authorize;

import com.liuly.security.core.properties.SecurityConstraints;
import com.liuly.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/15
 * @since JDK 1.8
 */
@Component
//@Order(Integer.MIN_VALUE)
public class CoreAuthorizeConfigPorvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(SecurityConstraints.DEFAULT_UNAUTHENTICATION_URL,
                securityProperties.getBrowser().getLoginPage(),
                securityProperties.getBrowser().getSignUpUrl(),
                SecurityConstraints.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                "/user/regist","/social/user")
                .permitAll();
    }
}
