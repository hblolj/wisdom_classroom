package com.yszn.security.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yszn.security.core.support.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: hblolj
 * @Date: 2018/5/7 9:14
 * @Description:
 * @Version: 1.0
 **/
@Component("authenticationEntryPoint")
public class MyAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
        logger.info(e.getMessage());
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if (e.getClass().equals(BadCredentialsException.class)){
            // TODO: 2018/4/21 如果是 html 引导到登录页面，其他返回 Json
            JsonData<String> jsonData = new JsonData<>(401, e.getMessage(), "未认证登录，请登录之后再来!");
            out.write(objectMapper.writeValueAsString(jsonData));
//            out.write("{\"status\":\"error\",\"msg\":\"没有登录，请先登录\"}");
        }else if (e.getClass().equals(AccessDeniedException.class)){
            out.write("{\"status\":\"error\",\"msg\":\"权限不足，请联系管理员!\"}");
        }else if (e instanceof InsufficientAuthenticationException){
            logger.info("Token 超时失效");
        }else {
            out.write("{\"status\":\"error\",\"msg\":\"" + e.getClass() + "\"}");
        }
        out.flush();
        out.close();
    }
}
