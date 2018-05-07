package com.yszn.security.rbac.service.impl;

import com.yszn.security.rbac.dao.SysAclDataMapper;
import com.yszn.security.rbac.dao.SysAclMapper;
import com.yszn.security.rbac.dao.SysUserAclDataMapper;
import com.yszn.security.rbac.model.SysAcl;
import com.yszn.security.rbac.model.SysAclData;
import com.yszn.security.rbac.model.SysUser;
import com.yszn.security.rbac.model.SysUserAclData;
import com.yszn.security.rbac.service.RbacService;
import com.yszn.security.rbac.service.SysCoreService;
import com.yszn.security.rbac.utils.SysAclDataAnalyzeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: hblolj
 * @Date: 2018/5/2 9:13
 * @Description: 鉴权 Rbac
 * @Version: 1.0
 **/
@Component("rbacService")
public class RbacServiceImpl implements RbacService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysAclMapper sysAclMapper;

    @Autowired
    private SysUserAclDataMapper sysUserAclDataMapper;

    @Autowired
    private SysAclDataMapper sysAclDataMapper;

    @Autowired
    private SysCoreService sysCoreService;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        logger.info("权限鉴定!!!");
        Map<String, String[]> requestMap = request.getParameterMap();
        if (authentication instanceof AnonymousAuthenticationToken){
            throw new BadCredentialsException("未登录");
        }
        String requestURI = request.getRequestURI();
        logger.info("请求地址: " + requestURI);
        // 请求所需要的权限 Id 集合
        List<Long> sysAclIds = new ArrayList<>();
        // 判断请求的 Url 是否需要权限，需要就返回需要的权限集合，不需要的返回登录权限
        List<SysAcl> sysAcls = sysAclMapper.selectByUrl(requestURI);
        if (!CollectionUtils.isEmpty(sysAcls)){
            String[] values = new String[sysAcls.size()];
            for (int i=0; i<sysAcls.size(); i++){
                if (sysAcls.get(i) != null){
                    values[i] = String.valueOf(sysAcls.get(i).getId());
                    sysAclIds.add(Long.parseLong(values[i]));
                }
            }
        }
        if (sysAclIds.size() == 0){
            // 不需要其他权限，只要登录就可以访问
            return true;
        }

        SysUser user = (SysUser) authentication.getPrincipal();

        // 获取用户角色权限 先从缓存中获取，如果缓存中没有再从数据库获取
        List<SysAcl> aclList = sysCoreService.getCurrentUserAclListFromCache(user.getId());
        Set<Long> userAclIdSet = aclList.stream()
                .map(SysAcl::getId)
                .collect(Collectors.toSet());

        //获取用户被限制的权限Ids
        Set<Long> limitIdSet = sysAclDataMapper.selectAclDataByUserId(user.getId())
                .stream()
                .map(SysAclData::getAclId)
                .collect(Collectors.toSet());

        userAclIdSet.addAll(limitIdSet);

        boolean hasValidAcl = false;
        // 遍历需要的权限，进行鉴权
        for (Long needAclId : sysAclIds){
            //当前请求需要的权限
            SysAcl acl = sysAclMapper.selectByPrimaryKey(needAclId);
            /**
             * 可选策略:
             * 只有有一个权限点符合就通过(独裁)
             * 所有权限点都符合才通过(联合)
             * 通过的权限点数量大于等于没通过的权限点数量才通过(少数服从多数)
             * 当前采用独裁模式，之后考虑设置为可配置
             */
            if (acl == null || acl.getStatus() != 1){
                continue;
            }
            hasValidAcl = true;
            if (StringUtils.equals("1", String.valueOf(acl.getBindPhone()))){
                // 当前 Url 需要绑定手机号后才能访问(匿名用户不能访问)
                if (StringUtils.isEmpty(user.getTelephone())){
                    // 当前用户为匿名用户
                    continue;
                }
            }
            if (userAclIdSet.contains(acl.getId())){
                // 进行数据权限的校验
                // 根据权限限制 Id 和用户 Id 到 sys_user_acl_data 中查询是否有记录，记录是否在有效时间内
                List<SysUserAclData> userLimits = sysUserAclDataMapper.selectByUserIdAndAclId(user.getId(), acl.getId());
                // 如果有记录且有效，将限制条件与当前 Request 中的参数进行比对匹配，最后决定是否通过
                List<SysAclData> sysAclDataList = new ArrayList<>();
                for (SysUserAclData ul : userLimits){
                    if ("1".equals(ul.getStatus())){
                        //限时条件，校验是否在有效时间之内
                        Date startTime = ul.getStartTime();
                        Date endTime = ul.getEndTime();

                        ZoneId zoneId = ZoneId.systemDefault();
                        LocalDate localDate = LocalDate.now();
                        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
                        Date now = Date.from(zdt.toInstant());

                        if (!(now.after(startTime) && now.before(endTime))){
                            // 不在有效时间之内，条件无效
                            continue;
                        }
                    }
                    //按限制规则进行校验
                    SysAclData limit = sysAclDataMapper.selectByPrimaryKey(ul.getAclDataId());
                    if ("1".equals(limit.getStatus())){
                        sysAclDataList.add(limit);
                    }
                }
                // 抽象出一个限制条件解析方法，传入一个权限点所有的限制于当前请求的参数，返回结果是Boolean
                Boolean visit = SysAclDataAnalyzeUtils.validateAclCanVisitByLimit(sysAclDataList, requestMap);
                if (visit){
                    return true;
                }
            }
        }

        if (!hasValidAcl){
            // 虽然请求的资源配置有权限限制，但是这些权限限制都已经失效了 status = 0(冻结) or null(删除)
            return true;
        }

        return false;
    }
}
