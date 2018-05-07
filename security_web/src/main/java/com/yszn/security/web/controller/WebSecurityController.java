package com.yszn.security.web.controller;

import com.yszn.security.core.properties.base.SecurityProperties;
import com.yszn.security.core.support.JsonData;
import org.apache.commons.lang.StringUtils;
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
 * @Date: 2018/5/2 16:12
 * @Description:
 * @Version: 1.0
 **/
@RestController
public class WebSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)//401
    @GetMapping("/authentication/require")
    public void requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO: 2018/5/2 当需要登录时，会访问该方法。微信无缝登录
        String user_agent = request.getHeader("user_agent");
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null){
            // TODO: 2018/4/21 如果是访问 html 请求，引导到登录页面，否则返回提示 JSON
            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("登录前请求的路径: " + redirectUrl);
            //跳转到一个登录页，动态配置
//          String loginPage = securityProperties.getBrowser().getLoginPage();
            String loginPage = "/default-signIn.html";
            System.out.println("获取到的登录页: " + loginPage);
            redirectStrategy.sendRedirect(request, response, loginPage);
        }
    }
}
