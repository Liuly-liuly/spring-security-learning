package com.liuly.security.browser.validate.code.repository;

import com.liuly.security.core.enums.ValidateCodeType;
import com.liuly.security.core.validate.ValidateCodeRepository;
import com.liuly.security.core.validate.code.ValidateCode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/7
 * @since JDK 1.8
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, ValidateCode validateCode, ValidateCodeType validateCodeType) {
         sessionStrategy.setAttribute(request, getSessionKey(validateCodeType), validateCode);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return  (ValidateCode) sessionStrategy.getAttribute(request,  getSessionKey(validateCodeType));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
           sessionStrategy.removeAttribute(request,  getSessionKey(validateCodeType));
    }

    private  String getSessionKey(ValidateCodeType validateCodeType){
        return SESSION_KEY_PREFIX+ validateCodeType.toString().toUpperCase();
    }

}
