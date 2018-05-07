package com.yszn.weixin.authorize;

import com.yszn.security.core.authentication.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author: hblolj
 * @Date: 2018/5/7 17:22
 * @Description: 微信模块访问权限配置
 * @Version: 1.0
 **/
@Component
public class WeiXinAuthorizeConfigProvider implements AuthorizeConfigProvider{

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) throws Exception {
        return false;
    }
}
