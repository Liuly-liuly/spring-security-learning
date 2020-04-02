package com.liuly.security.core.validate.generator;

import com.liuly.security.core.validate.code.ImageCode;
import com.liuly.security.core.validate.code.ValidateCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/2
 * @since JDK 1.8
 */
public interface ValidateCodeGenerator {
     ValidateCode generatorCode(HttpServletRequest request);
}
