package com.liuly.security.browser.system.authencationHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuly.security.core.enums.LoginType;
import com.liuly.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
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
@Component("LiulyAuthenticationFailHandler")
public class LiulyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;

    public LiulyAuthenticationFailHandler() {
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        this.logger.info("登陆失败");
        if (this.securityProperties.getBrowser().getLoginType().equals(LoginType.JSON)) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(this.objectMapper.writeValueAsString(exception.getMessage()));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}
