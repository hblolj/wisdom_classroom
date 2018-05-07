package com.yszn.security.rbac.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_role")
public class SysRole {
    /**
     * 角色id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * 角色的类型，1：管理员角色，2：其他
     */
    private Integer type;

    /**
     * 状态，1：可用，0：冻结
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
     * 最后一次更新的时间
     */
    @Column(name = "operate_time")
    private Date operateTime;

    /**
     * 最后一次更新者的ip地址
     */
    @Column(name = "operate_ip")
    private String operateIp;

    /**
     * 获取角色id
     *
     * @return id - 角色id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置角色id
     *
     * @param id 角色id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取角色的类型，1：管理员角色，2：其他
     *
     * @return type - 角色的类型，1：管理员角色，2：其他
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置角色的类型，1：管理员角色，2：其他
     *
     * @param type 角色的类型，1：管理员角色，2：其他
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取状态，1：可用，0：冻结
     *
     * @return status - 状态，1：可用，0：冻结
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态，1：可用，0：冻结
     *
     * @param status 状态，1：可用，0：冻结
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
     * 获取最后一次更新者的ip地址
     *
     * @return operate_ip - 最后一次更新者的ip地址
     */
    public String getOperateIp() {
        return operateIp;
    }

    /**
     * 设置最后一次更新者的ip地址
     *
     * @param operateIp 最后一次更新者的ip地址
     */
    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }
}