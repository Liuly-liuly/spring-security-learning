package com.security.authorize.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/15
 * @since JDK 1.8
 */
public interface RbacService {

    boolean hasPermissons(HttpServletRequest request, Authentication authentication);

}
