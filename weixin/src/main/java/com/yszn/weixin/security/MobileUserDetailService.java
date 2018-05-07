package com.yszn.weixin.security;

import com.yszn.security.rbac.dao.SysAclMapper;
import com.yszn.security.rbac.dao.SysUserMapper;
import com.yszn.security.rbac.model.SysAcl;
import com.yszn.security.rbac.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Description: 手机短信登录验证
 * @Version: 1.0
 **/
@Component("mobileUserDetailService")
public class MobileUserDetailService implements UserDetailsService{

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysAclMapper sysAclMapper;

    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
        // 按手机号查询用户
        SysUser sysUser = sysUserMapper.selectByMobile(mobileNumber);
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
