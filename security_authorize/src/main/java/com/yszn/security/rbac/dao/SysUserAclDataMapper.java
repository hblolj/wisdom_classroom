package com.yszn.security.rbac.dao;

import com.yszn.security.rbac.model.SysUserAclData;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysUserAclDataMapper extends Mapper<SysUserAclData> {

    List<SysUserAclData> selectByUserIdAndAclId(@Param("userId") Long userId, @Param("aclId") Long aclId);

}