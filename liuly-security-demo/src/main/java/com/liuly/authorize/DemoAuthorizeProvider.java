package com.liuly.authorize;

import com.liuly.security.core.authorize.AuthorizeConfigProvider;
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
//@Order(Integer.MAX_VALUE)
public class DemoAuthorizeProvider implements AuthorizeConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
       config.antMatchers("/demo.html").hasRole("ADMIN");
//        config.anyRequest().access("isAuthenticated() and @rbacService.hasPermissons(request,authentication)");
    }
}
