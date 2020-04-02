package com.liuly.security.browser.system.authencationHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuly.security.browser.system.ResponseData;
import com.liuly.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/5
 * @since JDK 1.8
 */
public class LiulyLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private SecurityProperties securityProperties;

    private ObjectMapper objectMapper = new ObjectMapper();

    public LiulyLogoutSuccessHandler(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("退出登录");
        String signOutUrl = securityProperties.getBrowser().getSignOutUrl();
        if(StringUtils.isBlank(signOutUrl)){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new ResponseData("退出登录成功")));
        }else {
            response.sendRedirect(signOutUrl);
        }
    }
}
