package com.liuly.security.core.validate.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/2
 * @since JDK 1.8
 */
public class ValidateException extends AuthenticationException {

    public ValidateException(String msg) {
        super(msg);
    }

}
