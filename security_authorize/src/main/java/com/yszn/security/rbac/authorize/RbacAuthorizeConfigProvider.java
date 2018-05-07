package com.yszn.security.rbac.authorize;

import com.yszn.security.core.authentication.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author: hblolj
 * @Date: 2018/5/2 9:17
 * @Description:
 * @Version: 1.0
 **/
@Component
@Order(Integer.MAX_VALUE)
public class RbacAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry httpSecurity) {
        httpSecurity
                .anyRequest()
                .access("@rbacService.hasPermission(request, authentication)");
        return true;
    }
}
