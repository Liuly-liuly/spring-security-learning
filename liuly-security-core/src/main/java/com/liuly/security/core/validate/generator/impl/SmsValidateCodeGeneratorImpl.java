package com.liuly.security.core.validate.generator.impl;

import com.liuly.security.core.properties.SecurityProperties;
import com.liuly.security.core.validate.code.ImageCode;
import com.liuly.security.core.validate.code.ValidateCode;
import com.liuly.security.core.validate.generator.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/2
 * @since JDK 1.8
 */
@Component("smsCodeGenerator")
public class SmsValidateCodeGeneratorImpl implements ValidateCodeGenerator{

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generatorCode(HttpServletRequest request) {
       String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code,securityProperties.getCode().getSms().getExpireIn());
    }
}
