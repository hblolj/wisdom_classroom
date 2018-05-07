package com.yszn.security.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: hblolj
 * @Date: 2018/5/6 12:45
 * @Description:
 * @Version: 1.0
 **/
@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    private final UserDetailsService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfiguration(@Qualifier("userDetailSevice") UserDetailsService userDetailsService,
                                    PasswordEncoder passwordEncoder) {
        this.userService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
