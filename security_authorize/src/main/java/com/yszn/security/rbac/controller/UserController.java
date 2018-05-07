package com.yszn.security.rbac.controller;

import com.yszn.security.core.support.JsonData;
import com.yszn.security.rbac.dto.UserInfo;
import com.yszn.security.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: hblolj
 * @Date: 2018/5/2 11:09
 * @Description:
 * @Version: 1.0
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public JsonData<Boolean> register(UserInfo userInfo){
        Boolean result = userService.register(userInfo);
        return JsonData.success(result, result ? "Success!" : "fail!");
    }
}
