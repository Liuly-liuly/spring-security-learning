package com.liuly.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/4
 * @since JDK 1.8
 */
//@Component
public class liuLySpringSocialSecurityConfig extends SpringSocialConfigurer {

    private String filterProcessesUrl; //自定义拦截器处理及转跳的路径

    private SocialAuthenticationFilterSuccessHander socialAuthenticationFilterSuccessHander;

//    public liuLySpringSocialSecurityConfig(){}

    public liuLySpringSocialSecurityConfig(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        if(socialAuthenticationFilterSuccessHander != null) socialAuthenticationFilterSuccessHander.setSuccessHandler(filter);
        return (T) filter;
    }

    public SocialAuthenticationFilterSuccessHander getSocialAuthenticationFilterSuccessHander() {
        return socialAuthenticationFilterSuccessHander;
    }

    public void setSocialAuthenticationFilterSuccessHander(SocialAuthenticationFilterSuccessHander socialAuthenticationFilterSuccessHander) {
        this.socialAuthenticationFilterSuccessHander = socialAuthenticationFilterSuccessHander;
    }
}
