package com.yszn.weixin.security;

import com.yszn.security.rbac.dao.SysAclMapper;
import com.yszn.security.rbac.dao.SysUserMapper;
import com.yszn.security.rbac.model.SysAcl;
import com.yszn.security.rbac.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author: hblolj
 * @Date: 2018/5/2 10:29
 * @Description:
 * @Version: 1.0
 **/
@Order(1)
@Component("userDetailSevice")
public class FormUserDetailSevice implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysAclMapper sysAclMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.selectByUserName(username);
        if (sysUser == null){
            throw new UsernameNotFoundException("介个用户不存在!");
        }
        List<SysAcl> aclList = sysAclMapper.selectByUser(sysUser.getId());
        Collection<SimpleGrantedAuthority> collection = new HashSet<>();
        aclList.forEach(acl -> collection.add(new SimpleGrantedAuthority(String.valueOf(acl.getId()))));

        sysUser.setAuthorities(collection);
        return sysUser;
    }
}
