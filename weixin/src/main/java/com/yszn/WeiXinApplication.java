package com.yszn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hblolj
 * @Date: 2018/4/29 17:39
 * @Description:
 * @Version: 1.0
 **/
@Configuration
@SpringBootApplication
@ComponentScan
@MapperScan(value = {"com.yszn.security.rbac.dao"})
public class WeiXinApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeiXinApplication.class, args);
    }

}
