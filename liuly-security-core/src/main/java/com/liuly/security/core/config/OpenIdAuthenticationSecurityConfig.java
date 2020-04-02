package com.liuly.security.core.config;

import com.liuly.security.core.authentication.openId.OpenIdAuthenticationFilter;
import com.liuly.security.core.authentication.openId.OpenIdAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/7
 * @since JDK 1.8
 */
@Component
public class OpenIdAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private SocialUserDetailsService userDetailsService;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        OpenIdAuthenticationFilter openIdAuthenticationFilter = new OpenIdAuthenticationFilter();

        openIdAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));

        openIdAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        openIdAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);

        OpenIdAuthenticationProvider authenticationProvider = new OpenIdAuthenticationProvider();

        authenticationProvider.setUsersConnectionRepository(usersConnectionRepository);

        authenticationProvider.setUserDetailsService(userDetailsService);

        builder.authenticationProvider(authenticationProvider)
                .addFilterAfter(openIdAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}