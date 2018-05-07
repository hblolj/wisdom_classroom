package com.yszn.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author: hblolj
 * @Date: Create in 14:37 2017/11/9
 * @Description: 短信登录配置
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler webAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler webAuthenticationFairedHandler;

    @Autowired
    private UserDetailsService mobileUserDetailService;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        SmsCodeAuthenticationFilter filter = new SmsCodeAuthenticationFilter();
        filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationSuccessHandler(webAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(webAuthenticationFairedHandler);
        String key = UUID.randomUUID().toString();
        filter.setRememberMeServices(new PersistentTokenBasedRememberMeServices(key, mobileUserDetailService, persistentTokenRepository));

        SmsCodeAuthenticationProvider provider = new SmsCodeAuthenticationProvider();
        provider.setUserDetailsService(mobileUserDetailService);

        http.authenticationProvider(provider).addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
