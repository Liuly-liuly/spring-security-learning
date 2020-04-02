package com.liuly.security.app.social;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.*;
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
public class AppSignUtils {

    @Autowired
    private RedisTemplate <Object,Object> redisTemplate;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    public  void  saveConnectionData(ServletWebRequest request , ConnectionData data){

        redisTemplate.opsForValue().set(getKey(request),data,10, TimeUnit.MINUTES);

    }

    public void doPostSignUp(ServletWebRequest request ,String userId){
        String key = getKey(request);

        if(!redisTemplate.hasKey(key))throw  new RuntimeException("无法找到设备的缓存信息");

        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);

        ConnectionFactory<?> connectionFactory = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId());

        Connection<?> connection = connectionFactory.createConnection(connectionData);

        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);

        redisTemplate.delete(key);

    }

    private String getKey(ServletWebRequest request) {

        String deviceId = request.getHeader("deviceId");

        if(StringUtils.isBlank(deviceId)) throw  new RuntimeException("设备id不能为空");

        return "liuly:app:connect:sign:code"+deviceId;

    }

}
