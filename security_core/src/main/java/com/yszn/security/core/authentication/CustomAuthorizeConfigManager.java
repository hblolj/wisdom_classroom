package com.yszn.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: hblolj
 * @Date: 2018/5/6 14:29
 * @Description:
 * @Version: 1.0
 **/
@Component
public class CustomAuthorizeConfigManager implements AuthorizeConfigManager {

    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) throws Exception {
        boolean existAnyRequestConfig = false;
        String existAnyRequestConfigName = null;
        for (AuthorizeConfigProvider provider : authorizeConfigProviders){
            boolean currentIsAnyRequestConfig = provider.config(config);
            if (existAnyRequestConfig && currentIsAnyRequestConfig){
                throw new RuntimeException("重复的anyRequest配置: " + existAnyRequestConfigName + ","
                        + provider.getClass().getSimpleName());
            }else if (currentIsAnyRequestConfig){
                existAnyRequestConfig = true;
                existAnyRequestConfigName = provider.getClass().getName();
            }
        }
    }
}
