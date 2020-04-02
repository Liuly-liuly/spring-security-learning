package com.liuly.security.app.social;

import com.liuly.security.core.social.liuLySpringSocialSecurityConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/7
 * @since JDK 1.8
 */
@Component
public class SpringSocialConfigBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (StringUtils.equals(beanName, "liuLySocialSecurityConfig")) {
            liuLySpringSocialSecurityConfig springSocialSecurityConfig = (liuLySpringSocialSecurityConfig) bean;
            springSocialSecurityConfig.signupUrl("/social/signUp");
            return springSocialSecurityConfig;
        }
        return bean;
    }
}
