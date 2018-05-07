package com.yszn.security.core.authentication;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author: hblolj
 * @Date: 2018/5/2 9:20
 * @Description: 授权信息管理器 用于收集系统中所有 AuthorizeConfigProvider 并加载其配置
 * @Version: 1.0
 **/
public interface AuthorizeConfigManager {

    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) throws Exception;
}
