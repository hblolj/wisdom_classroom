package com.yszn.weixin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hblolj
 * @Date: 2018/5/2 9:53
 * @Description:
 * @Version: 1.0
 **/
@RestController
public class DemoController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/demo")
    public String demo(){
        String encode = passwordEncoder.encode("123456");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encode;
    }
}
