package com.liuly.security.app.config;

import com.liuly.security.app.config.jwtEnhancer.JwtTokenEnhancer;
import com.liuly.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.validation.groups.ConvertGroup;
import java.util.Collection;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/12
 * @since JDK 1.8
 */
@Configuration
public class TokenStoreConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @ConditionalOnProperty(prefix = "com.liuly.security.oauth2",name = "storeType",havingValue = "redis")
    public TokenStore tokenStore(){
       return  new RedisTokenStore(redisConnectionFactory);
    }

    @Configuration
    @ConditionalOnProperty(prefix = "com.liuly.security.oauth2",name = "storeType",havingValue = "jwt",matchIfMissing = true)
    public static  class JwtConfig{

        @Autowired
        private  SecurityProperties securityProperties;

        @Bean
        public  TokenStore JwtTokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey(securityProperties.getOauth2().getJwrSignKey());//签名的密钥
            return converter;
        }

        @Bean
        @ConditionalOnMissingBean(TokenEnhancer.class)
        public TokenEnhancer tokenEnhancer(){
            return new JwtTokenEnhancer();
        }


    }

}
