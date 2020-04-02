package com.liuly.security.core.validate.processor;

import com.liuly.security.core.validate.exception.ValidateException;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/3
 * @since JDK 1.8
 */
public interface ValidateCodeProcessor {

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    void create(ServletWebRequest request) throws Exception;

    void validate(ServletWebRequest request) throws ValidateException;
}
