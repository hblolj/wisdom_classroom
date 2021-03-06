package com.yszn.security.rbac.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_acl_module")
public class SysAclModule {
    /**
     * 权限模块id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 权限模块名称
     */
    private String name;

    /**
     * 上级权限模块id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 权限模块层级
     */
    private String level;

    /**
     * 权限模块在当前层级下的顺序，由小到大
     */
    private Integer seq;

    /**
     * 状态，1：正常，0：冻结
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 最后一次操作时间
     */
    @Column(name = "operate_time")
    private Date operateTime;

    /**
     * 最后一次更新操作者的ip地址
     */
    @Column(name = "operate_ip")
    private String operateIp;

    /**
     * 获取权限模块id
     *
     * @return id - 权限模块id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置权限模块id
     *
     * @param id 权限模块id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取权限模块名称
     *
     * @return name - 权限模块名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置权限模块名称
     *
     * @param name 权限模块名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取上级权限模块id
     *
     * @return parent_id - 上级权限模块id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置上级权限模块id
     *
     * @param parentId 上级权限模块id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取权限模块层级
     *
     * @return level - 权限模块层级
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置权限模块层级
     *
     * @param level 权限模块层级
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取权限模块在当前层级下的顺序，由小到大
     *
     * @return seq - 权限模块在当前层级下的顺序，由小到大
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置权限模块在当前层级下的顺序，由小到大
     *
     * @param seq 权限模块在当前层级下的顺序，由小到大
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取状态，1：正常，0：冻结
     *
     * @return status - 状态，1：正常，0：冻结
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态，1：正常，0：冻结
     *
     * @param status 状态，1：正常，0：冻结
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
     * 获取最后一次操作时间
     *
     * @return operate_time - 最后一次操作时间
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * 设置最后一次操作时间
     *
     * @param operateTime 最后一次操作时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取最后一次更新操作者的ip地址
     *
     * @return operate_ip - 最后一次更新操作者的ip地址
     */
    public String getOperateIp() {
        return operateIp;
    }

    /**
     * 设置最后一次更新操作者的ip地址
     *
     * @param operateIp 最后一次更新操作者的ip地址
     */
    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }
}