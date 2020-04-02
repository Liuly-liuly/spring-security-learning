package com.liuly.security.core.validate.processor;

import com.liuly.security.core.enums.ValidateCodeType;
import com.liuly.security.core.validate.exception.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/3
 * @since JDK 1.8
 */
//@Component
public class ValidateCodeProcessorHolder {

    //@Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName(); //此处无法找到这个bean，需要通过类名去寻找
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidateException("验证码处理器" + name + "不存在");
        }
        return processor;
    }

}
