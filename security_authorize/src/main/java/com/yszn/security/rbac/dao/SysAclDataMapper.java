package com.yszn.security.rbac.dao;

import com.yszn.security.rbac.model.SysAclData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysAclDataMapper extends Mapper<SysAclData> {

    List<SysAclData> selectAclDataByUserId(Long userId);

}