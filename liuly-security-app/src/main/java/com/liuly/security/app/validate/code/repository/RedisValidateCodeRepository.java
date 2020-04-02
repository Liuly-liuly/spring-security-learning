package com.liuly.security.app.validate.code.repository;

import com.liuly.security.core.enums.ValidateCodeType;
import com.liuly.security.core.validate.ValidateCodeRepository;
import com.liuly.security.core.validate.code.ValidateCode;
import com.liuly.security.core.validate.exception.ValidateException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/7
 * @since JDK 1.8
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository{

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public void save(ServletWebRequest request, ValidateCode validateCode, ValidateCodeType validateCodeType) {
        redisTemplate.opsForValue().set(buildKey(request,validateCodeType),validateCode, 10, TimeUnit.MINUTES);
    }

    private String buildKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        String deviceId = request.getHeader("deviceId");
        if(StringUtils.isBlank(deviceId)){
            throw new ValidateException("设备id为空请在请求头中带入");
        }
        return validateCodeType.name()+"validate_code"+deviceId;
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) redisTemplate.opsForValue().get(buildKey(request,validateCodeType));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        redisTemplate.delete(buildKey(request,validateCodeType));
    }
}
