package com.liuly.security.app.config.jwtEnhancer;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/12
 * @since JDK 1.8
 */
public class JwtTokenEnhancer implements TokenEnhancer {

    //往jwt添加信息
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String ,Object> result = new HashMap<>();
        result.put("liuly","liuly");
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(result);
        return accessToken;
    }

}
