package com.liuly.security.app.social;

import com.liuly.security.core.social.SocialAuthenticationFilterSuccessHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/7
* @since JDK 1.8
 */
@Component
public class AppSetSocialFilterSuccessHandler implements SocialAuthenticationFilterSuccessHander {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    public void setSuccessHandler(SocialAuthenticationFilter authenticationFilter) {
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
    }
}
