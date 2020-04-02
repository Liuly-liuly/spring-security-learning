package com.liuly.security.core.enums;

import com.liuly.security.core.properties.SecurityConstraints;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/3
 * @since JDK 1.8
 */
public enum ValidateCodeType {

    SMS {
        @Override
        public String getParameterNameOnValidateCode() {
            return SecurityConstraints.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },

    IMAGE{
        @Override
        public String getParameterNameOnValidateCode() {
            return SecurityConstraints.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };


    public abstract String getParameterNameOnValidateCode();

}
