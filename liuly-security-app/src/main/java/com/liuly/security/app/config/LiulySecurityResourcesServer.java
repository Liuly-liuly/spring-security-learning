package com.liuly.security.app.config;

import com.liuly.security.core.authorize.AuthorizeConfigManager;
import com.liuly.security.core.config.OpenIdAuthenticationSecurityConfig;
import com.liuly.security.core.config.SmsCodeAuthenticationSecurityConfig;
import com.liuly.security.core.properties.SecurityConstraints;
import com.liuly.security.core.properties.SecurityProperties;
import com.liuly.security.core.validate.ValidateCodeFilterSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/6
 * @since JDK 1.8
 */
@Configuration
@EnableResourceServer
public class LiulySecurityResourcesServer extends ResourceServerConfigurerAdapter{

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeFilterSecurityConfig validateCodeFilterSecurityConfig;

    @Autowired
    private SpringSocialConfigurer liuLySocialSecurityConfig;

    @Autowired
    protected AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginProcessingUrl(SecurityConstraints.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .apply(validateCodeFilterSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(liuLySocialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
//                .authorizeRequests()
//                .antMatchers(SecurityConstraints.DEFAULT_UNAUTHENTICATION_URL,
//                        SecurityConstraints.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
//                        "/user/regist","/social/user").permitAll()
//                .anyRequest().access("authenticated() and hasRole('ADMIN')")
//                .and()
                .csrf().disable();
        authorizeConfigManager.config(http.authorizeRequests());
    }

}
