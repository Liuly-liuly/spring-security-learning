package com.security.authorize.rbac.impl;

import com.security.authorize.rbac.RbacService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/15
 * @since JDK 1.8
 */
@Component("rbacService")
public class RbacServiceImpl implements RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermissons(HttpServletRequest request, Authentication authentication) {

        String requestURI = request.getRequestURI();

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {

            String username = ((UserDetails) principal).getUsername();
            //这里通过查寻数据库中的资源信表去查找该用户是否有这个权限
            Set<String> sets = new HashSet<>();
            for (String url : sets) {
                if (antPathMatcher.match(url, requestURI)) return true;
            }
        }

        return false;

    }
}
