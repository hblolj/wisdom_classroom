package com.yszn.security.web.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author: hblolj
 * @Date: 2018/5/2 10:13
 * @Description:
 * @Version: 1.0
 **/
@Component("webAuthenticationFairedHandler")
public class WebAuthenticationFairedHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // TODO: 2018/5/2 认证失败处理
        response.setCharacterEncoding(Charset.forName("UTF-8").toString());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        logger.info("认证失败: " + exception.getMessage());
        super.onAuthenticationFailure(request, response, exception);
    }
}
