package com.sso.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/14
 * @since JDK 1.8
 */
@SpringBootApplication
@RestController
@EnableOAuth2Sso
public class AppApplication {

    @GetMapping("/user")
    public Authentication getUser(Authentication user){
        return user;
    }

    public static void main(String [] args){
        SpringApplication.run(AppApplication.class,args);
    }

}

