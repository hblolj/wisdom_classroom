package com.yszn.security.app.controller;

import com.yszn.security.core.properties.base.SecurityProperties;
import com.yszn.security.core.support.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: hblolj
 * @Date: 2018/5/4 14:44
 * @Description:
 * @Version: 1.0
 **/
@RestController
public class AppSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)//401
    @GetMapping("/authentication/require")
    public JsonData requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 当需要登录时，会访问该方法。微信无缝登录
        return JsonData.fail("请先登录");
    }
}
