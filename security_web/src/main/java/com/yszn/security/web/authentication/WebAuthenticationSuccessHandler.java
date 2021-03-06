package com.yszn.security.web.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: hblolj
 * @Date: 2018/5/2 10:13
 * @Description:
 * @Version: 1.0
 **/
@Component("webAuthenticationSuccessHandler")
public class WebAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        // TODO: 2018/5/2 认证成功处理
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
