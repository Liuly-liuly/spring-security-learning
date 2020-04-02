package com.liuly.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/7
 * @since JDK 1.8
 */
public interface SocialAuthenticationFilterSuccessHander {

    void setSuccessHandler(SocialAuthenticationFilter authenticationFilter);

}
