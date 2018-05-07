package com.yszn.security.rbac.dao;

import com.yszn.security.rbac.model.SysAcl;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysAclMapper extends Mapper<SysAcl> {

    List<SysAcl> selectByUser(Long userId);

    List<SysAcl> selectByUrl(String url);
}