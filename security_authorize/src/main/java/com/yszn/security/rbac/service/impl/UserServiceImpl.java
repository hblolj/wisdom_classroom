package com.yszn.security.rbac.service.impl;

import com.yszn.security.core.constant.IdGeneratorConstant;
import com.yszn.security.core.service.UniqueIdGeneratorService;
import com.yszn.security.rbac.dao.SysUserMapper;
import com.yszn.security.rbac.dto.UserInfo;
import com.yszn.security.rbac.model.SysUser;
import com.yszn.security.rbac.service.UserService;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

/**
 * @author: hblolj
 * @Date: 2018/5/2 11:13
 * @Description:
 * @Version: 1.0
 **/
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UniqueIdGeneratorService uniqueIdGeneratorService;

    /**
     * 用户注册
     * @param userInfo
     * @return
     */
    @Override
    public Boolean register(UserInfo userInfo) {
        // TODO: 2018/5/2 Id 自增? 全局统一 Id 生成策略
        SysUser sysUser = new SysUser();
        try {
            Long userId = uniqueIdGeneratorService.fetchUniqueId(IdGeneratorConstant.USER, 11, false);
            // TODO: 2018/5/2 密码、Id、状态、备注、操作者、最后一次操作时间、最后一次操作 Ip
            String newPassword = passwordEncoder.encode(userInfo.getPassword());
            userInfo.setId(userId);
            userInfo.setStatus(1);
            userInfo.setPassword(newPassword);
            BeanCopier bc = BeanCopier.create(UserInfo.class, SysUser.class, false);
            bc.copy(userInfo, sysUser, null);
            sysUser.setOperator(String.valueOf(userId));
            sysUser.setOperateTime(Date.from(Instant.now()));
            sysUser.setOperateIp("127.0.0.1");
            int result = sysUserMapper.insert(sysUser);
            return result == 1;
        } catch (Exception e) {
            // TODO: 2018/5/2 生成全局唯一 id 失败
            e.printStackTrace();
        }
        return false;
    }
}
