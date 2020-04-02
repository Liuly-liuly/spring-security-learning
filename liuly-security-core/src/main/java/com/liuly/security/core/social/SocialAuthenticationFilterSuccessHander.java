package com.liuly.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/7
 * @since JDK 1.8
 */
public interface SocialAuthenticationFilterSuccessHander {

    void setSuccessHandler(SocialAuthenticationFilter authenticationFilter);

}
