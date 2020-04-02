package com.liuly.code;

import com.liuly.security.core.validate.code.ImageCode;
import com.liuly.security.core.validate.code.ValidateCode;
import com.liuly.security.core.validate.generator.ValidateCodeGenerator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/2
 * @since JDK 1.8
 */
//@Component("imageCodeGenerator")
public class ImageCodeGenerator implements ValidateCodeGenerator{
    @Override
    public ValidateCode generatorCode(HttpServletRequest request) {
        System.out.println("---->"+"自定义验证码");
        return null;
    }
}
