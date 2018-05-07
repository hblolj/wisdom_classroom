package com.yszn.security.rbac.dao;

import com.yszn.security.rbac.model.SysUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SysUserMapper extends Mapper<SysUser> {

    SysUser selectByUserName(String username);

    SysUser selectByMobile(String mobile);

    Integer updatePassword(@Param("username") String username, @Param("password") String password);
}