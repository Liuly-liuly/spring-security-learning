package com.liuly.security.core.social.qq.config;

import com.liuly.security.core.properties.QQProperties;
import com.liuly.security.core.properties.SecurityProperties;
import com.liuly.security.core.social.qq.connection.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/4
 * @since JDK 1.8
 */
@Component
@ConditionalOnProperty(prefix = "com.liuly.security.social.qq",name = "appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Autowired
    private DataSource dataSource;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qq.getProviderId(),
                qq.getAppId(),
                qq.getAppSecret());
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        //这里是没有加密就放入，需要更深去设置这个加密方式
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix("liuly_");
        if(connectionSignUp != null) repository.setConnectionSignUp(connectionSignUp);
        return repository;
    }
}
