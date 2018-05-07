package com.yszn.security.rbac.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user_acl_data")
public class SysUserAclData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 权限限制记录Id
     */
    @Column(name = "acl_data_id")
    private Long aclDataId;

    /**
     * 是否限时，1：限时，0：不限时
     */
    private String status;

    /**
     * 有效开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 有效结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 最后一次操作时间
     */
    @Column(name = "operator_time")
    private Date operatorTime;

    /**
     * 最后更新者的IP地址
     */
    @Column(name = "operator_ip")
    private String operatorIp;

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
     * 获取用户Id
     *
     * @return user_id - 用户Id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户Id
     *
     * @param userId 用户Id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取权限限制记录Id
     *
     * @return acl_data_id - 权限限制记录Id
     */
    public Long getAclDataId() {
        return aclDataId;
    }

    /**
     * 设置权限限制记录Id
     *
     * @param aclDataId 权限限制记录Id
     */
    public void setAclDataId(Long aclDataId) {
        this.aclDataId = aclDataId;
    }

    /**
     * 获取是否限时，1：限时，0：不限时
     *
     * @return status - 是否限时，1：限时，0：不限时
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置是否限时，1：限时，0：不限时
     *
     * @param status 是否限时，1：限时，0：不限时
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取有效开始时间
     *
     * @return start_time - 有效开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置有效开始时间
     *
     * @param startTime 有效开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取有效结束时间
     *
     * @return end_time - 有效结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置有效结束时间
     *
     * @param endTime 有效结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取最后一次操作时间
     *
     * @return operator_time - 最后一次操作时间
     */
    public Date getOperatorTime() {
        return operatorTime;
    }

    /**
     * 设置最后一次操作时间
     *
     * @param operatorTime 最后一次操作时间
     */
    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    /**
     * 获取最后更新者的IP地址
     *
     * @return operator_ip - 最后更新者的IP地址
     */
    public String getOperatorIp() {
        return operatorIp;
    }

    /**
     * 设置最后更新者的IP地址
     *
     * @param operatorIp 最后更新者的IP地址
     */
    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp;
    }
}