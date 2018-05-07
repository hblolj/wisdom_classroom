package com.yszn.security.rbac.service;

import com.yszn.security.rbac.model.SysAcl;

import java.util.List;

/**
 * @author: hblolj
 * @Date: 2018/5/7 11:23
 * @Description:
 * @Version: 1.0
 **/
public interface SysCoreService {

    List<SysAcl> getCurrentUserAclList(Long userId);

    List<SysAcl> getCurrentUserAclListFromCache(Long userId);
}
