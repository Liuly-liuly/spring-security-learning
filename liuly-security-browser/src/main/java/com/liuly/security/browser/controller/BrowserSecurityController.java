package com.liuly.security.browser.controller;

import com.liuly.security.browser.system.ResponseData;
import com.liuly.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/1
 * @since JDK 1.8
 */
@RestController
public class BrowserSecurityController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    //缓存在session 中的数据
    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;
    /**
     * description: 自动配置登录的页面才是最正确的选择
     * @auther: li.liu06@hand-china.com
     * @date: 2018/10/1 20:25
     */
    @RequestMapping("/authentication/security/required")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseData requestAuthenCation(HttpServletRequest request , HttpServletResponse response)throws IOException{
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if (savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("--->"+targetUrl);
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }
        return  new ResponseData("没有授权，请返回登录页");
    }

    @GetMapping("/social/user")
    public Object getSocialUserInfo(HttpServletRequest request) {
//        SocialUserInfo userInfo = new SocialUserInfo();
//        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
//       //这样写是不友好的。。。。。。
//        userInfo.setProviderId(connection.getKey().getProviderId());
//        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
//        userInfo.setNickname(connection.getDisplayName());
//        userInfo.setHeadimg(connection.getImageUrl());
//        return userInfo;
       return providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
    }


}
