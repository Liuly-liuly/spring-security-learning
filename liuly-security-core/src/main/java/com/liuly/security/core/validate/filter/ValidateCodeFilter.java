package com.liuly.security.core.validate.filter;

import com.liuly.security.core.enums.ValidateCodeType;
import com.liuly.security.core.properties.SecurityConstraints;
import com.liuly.security.core.properties.SecurityProperties;
import com.liuly.security.core.validate.code.ImageCode;
import com.liuly.security.core.validate.controller.ValidateCodeController;
import com.liuly.security.core.validate.exception.ValidateException;
import com.liuly.security.core.validate.processor.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @version 2.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/2
 * @since JDK 1.8
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private Map<String, ValidateCodeProcessor> processorMap;

    private Map<String, ValidateCodeType> validateCodeTypeMap = new HashMap<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        validateCodeTypeMap.put(SecurityConstraints.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        AnalyzeUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);

        validateCodeTypeMap.put(SecurityConstraints.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        AnalyzeUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);
    }

    private void AnalyzeUrlToMap(String urls, ValidateCodeType type) {
        String[] config = StringUtils.splitByWholeSeparatorPreserveAllTokens(urls, ",");
        if (config != null) {
            for (String url : config) {
                validateCodeTypeMap.put(url, type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        for (String url : validateCodeTypeMap.keySet()) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                try {
                    String name = validateCodeTypeMap.get(url).name().toLowerCase() + "CodeValidateProcessor";
                    processorMap.get(name).validate(new ServletWebRequest(request, response));
                } catch (ValidateException e) {
                    authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
