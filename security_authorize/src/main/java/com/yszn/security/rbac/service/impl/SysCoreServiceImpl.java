package com.yszn.security.rbac.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yszn.security.core.constant.CacheKeyConstants;
import com.yszn.security.core.util.ObjectMapperUtils;
import com.yszn.security.rbac.dao.SysAclMapper;
import com.yszn.security.rbac.dao.SysRoleAclMapper;
import com.yszn.security.rbac.dao.SysRoleUserMapper;
import com.yszn.security.rbac.model.SysAcl;
import com.yszn.security.core.service.SysCacheService;
import com.yszn.security.rbac.service.SysCoreService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: hblolj
 * @Date: 2018/5/7 11:24
 * @Description:
 * @Version: 1.0
 **/
@Service
public class SysCoreServiceImpl implements SysCoreService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysCacheService sysCacheService;

    @Autowired
    private SysRoleAclMapper sysRoleAclMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysAclMapper sysAclMapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<SysAcl> getCurrentUserAclList(Long userId) {
        return sysAclMapper.selectByUser(userId);
    }

    /**
     * 从内存中获取当前用户的权限
     * @return
     */
    @Override
    public List<SysAcl> getCurrentUserAclListFromCache(Long userId) {
        String cacheValue = sysCacheService.getFromCache(CacheKeyConstants.USER_ACLS, String.valueOf(userId));
        try {
            if (StringUtils.isBlank(cacheValue)) {
                List<SysAcl> aclList = getCurrentUserAclList(userId);
                if (CollectionUtils.isNotEmpty(aclList)) {
                    sysCacheService.saveCache(objectMapper.writeValueAsString(aclList), 600, CacheKeyConstants.USER_ACLS, String.valueOf(userId));
                }
                return aclList;
            }
            JavaType javaType = ObjectMapperUtils.getCollectionType(ArrayList.class, objectMapper, SysAcl.class);
            return (List<SysAcl>)objectMapper.readValue(cacheValue, javaType);
        } catch (JsonProcessingException e) {
            logger.error("保存用户权限到 Redis 中失败!", e);
        } catch (IOException e) {
            logger.error("cacheValue 转换成 权限集合失败!", e);
        }
        return null;
    }
}
