/**
 *
 */
package com.liuly.security.browser.config;

import com.liuly.security.core.authorize.AuthorizeConfigManager;
import com.liuly.security.core.config.AbstractSecurityCoreFilterConfig;
import com.liuly.security.core.config.SmsCodeAuthenticationSecurityConfig;
import com.liuly.security.core.properties.SecurityConstraints;
import com.liuly.security.core.properties.SecurityProperties;
import com.liuly.security.core.validate.ValidateCodeFilterSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author liuly
 */
@Configuration
public class BrowserSecurityConfig extends AbstractSecurityCoreFilterConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeFilterSecurityConfig validateCodeFilterSecurityConfig;

    @Autowired
    private SpringSocialConfigurer liuLySocialSecurityConfig;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);
        http.apply(validateCodeFilterSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(liuLySocialSecurityConfig)
                .and()
                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(securityProperties.getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()
                .logout()
                .logoutUrl("/signLogout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
//                .authorizeRequests()
//                .antMatchers(SecurityConstraints.DEFAULT_UNAUTHENTICATION_URL,
//                        securityProperties.getBrowser().getLoginPage(),
//                        securityProperties.getBrowser().getSignUpUrl(),
//                        SecurityConstraints.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
//                        "/user/regist").permitAll()
//                .anyRequest()
//                .access("authenticated() and hasRole('ADMIN')") //这里复合组合权限
//                .and()
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());

    }

}
