package com.liuly.security.core.validate;

import com.liuly.security.core.enums.ValidateCodeType;
import com.liuly.security.core.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/7
 * @since JDK 1.8
 */
public interface ValidateCodeRepository {

    void save (ServletWebRequest request , ValidateCode validateCode , ValidateCodeType validateCodeType);

    ValidateCode get(ServletWebRequest request , ValidateCodeType validateCodeType);

    void remove(ServletWebRequest request , ValidateCodeType validateCodeType);

}
