package com.liuly.security.core.social;

import com.liuly.security.core.properties.SecurityProperties;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;


/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/4
 * @since JDK 1.8
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter{

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Autowired(required = false)
    private SocialAuthenticationFilterSuccessHander socialAuthenticationFilterSuccessHander;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        //这里是没有加密就放入，需要更深去设置这个加密方式
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix("liuly_");
        if(connectionSignUp != null) repository.setConnectionSignUp(connectionSignUp);
        return repository;
    }

    @Bean(name = "liuLySocialSecurityConfig")
    public SpringSocialConfigurer liulySocialConfig(){
       //return new SpringSocialConfigurer();
        //自定义socialAuthenticationFilter 去处理自定义的路径
        liuLySpringSocialSecurityConfig springSocialConfigurer =  new liuLySpringSocialSecurityConfig(securityProperties.getSocial().getFilterProcessesUrl());
       //自定义注册，绑定页面
        springSocialConfigurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        //如果需要app中自定义的成功处理器，则需要加入
        springSocialConfigurer.setSocialAuthenticationFilterSuccessHander(socialAuthenticationFilterSuccessHander);

        return springSocialConfigurer;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator,
                getUsersConnectionRepository(connectionFactoryLocator));
    }

}
