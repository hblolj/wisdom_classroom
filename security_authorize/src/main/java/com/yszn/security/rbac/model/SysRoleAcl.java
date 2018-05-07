package com.yszn.security.rbac.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_role_acl")
public class SysRoleAcl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 权限id
     */
    @Column(name = "acl_id")
    private Long aclId;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 最后一次更新的时间
     */
    @Column(name = "operate_time")
    private Date operateTime;

    /**
     * 最后一次更新者的ip
     */
    @Column(name = "operate_ip")
    private String operateIp;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取权限id
     *
     * @return acl_id - 权限id
     */
    public Long getAclId() {
        return aclId;
    }

    /**
     * 设置权限id
     *
     * @param aclId 权限id
     */
    public void setAclId(Long aclId) {
        this.aclId = aclId;
    }

    /**
     * 获取操作者
     *
     * @return operator - 操作者
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作者
     *
     * @param operator 操作者
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取最后一次更新的时间
     *
     * @return operate_time - 最后一次更新的时间
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * 设置最后一次更新的时间
     *
     * @param operateTime 最后一次更新的时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取最后一次更新者的ip
     *
     * @return operate_ip - 最后一次更新者的ip
     */
    public String getOperateIp() {
        return operateIp;
    }

    /**
     * 设置最后一次更新者的ip
     *
     * @param operateIp 最后一次更新者的ip
     */
    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }
}