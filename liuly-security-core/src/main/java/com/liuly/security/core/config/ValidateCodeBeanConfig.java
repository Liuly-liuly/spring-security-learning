package com.liuly.security.core.config;

import com.liuly.security.core.properties.SecurityProperties;
import com.liuly.security.core.validate.generator.ValidateCodeGenerator;
import com.liuly.security.core.validate.generator.impl.ImageValidateCodeGeneratorImpl;
import com.liuly.security.core.validate.sms.SmsCodeSender;
import com.liuly.security.core.validate.sms.impl.SmsCodeSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/2
 * @since JDK 1.8
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean("imageCodeGenerator")
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator(){
       ImageValidateCodeGeneratorImpl imageValidateCodeGenerator = new ImageValidateCodeGeneratorImpl();
       imageValidateCodeGenerator.setSecurityProperties(securityProperties);
       return imageValidateCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        SmsCodeSenderImpl smsCodeSender = new SmsCodeSenderImpl();
        return smsCodeSender;
    }

}
