package com.yszn.security.rbac.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: hblolj
 * @Date: 2018/5/2 9:12
 * @Description:
 * @Version: 1.0
 **/
public interface RbacService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
