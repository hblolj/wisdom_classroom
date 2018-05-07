package com.yszn.security.rbac.service;

import com.yszn.security.rbac.dto.UserInfo;

/**
 * @author: hblolj
 * @Date: 2018/5/2 11:13
 * @Description:
 * @Version: 1.0
 **/
public interface UserService {

    Boolean register(UserInfo userInfo);
}
