package com.liuly.security.core.validate.processor;

import com.liuly.security.core.enums.ValidateCodeType;
import com.liuly.security.core.validate.ValidateCodeRepository;
import com.liuly.security.core.validate.code.ValidateCode;
import com.liuly.security.core.validate.exception.ValidateException;
import com.liuly.security.core.validate.generator.ValidateCodeGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/3
 * @since JDK 1.8
 */
public abstract class AbstractValidateCodeProcessor <C extends ValidateCode> implements ValidateCodeProcessor{

    @Autowired
    private Map<String , ValidateCodeGenerator> validateCodeGeneratorMap;

    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request,validateCode);
        send(request,validateCode);
    }

    private C generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        String generatorName = type + "CodeGenerator";
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateException("验证码生成器" + generatorName + "不存在");
        }
        return (C) validateCodeGenerator.generatorCode(request.getRequest());
    }

    private void save(ServletWebRequest request, C validateCode) {
        ValidateCode code = new ValidateCode(validateCode.getCode(),validateCode.getExpireTime());
        validateCodeRepository.save(request,code,getValidateCodeType(request));
   }


    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    private  String getProcessorType (ServletWebRequest request){
        return StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/");
    }

    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeValidateProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    @Override
    public void validate(ServletWebRequest request) throws ValidateException{
        ValidateCodeType validateCodeType = getValidateCodeType(request);
        C codeInSession = (C)  validateCodeRepository.get(request,validateCodeType);
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    validateCodeType.getParameterNameOnValidateCode());
        } catch (ServletRequestBindingException e) {
            throw new ValidateException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateException("验证码不存在");
        }

        if (codeInSession.isExpried()) {
            validateCodeRepository.remove(request,validateCodeType);
            throw new ValidateException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateException("验证码不匹配");
        }
        validateCodeRepository.remove(request,validateCodeType);
    }
}
