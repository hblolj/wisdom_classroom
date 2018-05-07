package com.yszn.security.core.authentication;

import com.yszn.security.core.properties.base.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author: hblolj
 * @Date: 2018/5/6 14:31
 * @Description:
 * @Version: 1.0
 **/
@Component
@Order(Integer.MIN_VALUE)
public class CustomAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) throws Exception {
        config.antMatchers(
                "/authentication/require",
                "/hblolj/form",
                "/session/invalid",
                "/code/image",
                "/connect/**",
                "/register",
                "/wx/login"
        ).permitAll();
        return false;
    }
}
