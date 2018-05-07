package com.yszn.security.web;

import com.yszn.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.yszn.security.core.config.AuthenticationAccessDeniedHandler;
import com.yszn.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author: hblolj
 * @Date: 2018/5/2 9:49
 * @Description: 浏览器端的 Security 认证配置
 * @Version: 1.0
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler webAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler webAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private UserDetailsService formUserDetailSevice;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    /**
     * Used by the default implementation of authenticationManager() to attempt to obtain an AuthenticationManager.
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(formUserDetailSevice).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .apply(validateCodeSecurityConfig).and()
                .apply(smsCodeAuthenticationSecurityConfig).and()
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(webAuthenticationSuccessHandler)
                .failureHandler(webAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/register", "/authentication/require", "/authentication/form").permitAll()
                .antMatchers("/code/sms", "/authentication/mobile", "/code/image").permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling()
                .accessDeniedHandler(authenticationAccessDeniedHandler).and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/*.html")
                .antMatchers("/css/*")
                .antMatchers("/js/*")
                .antMatchers("/img/*");
    }
}
