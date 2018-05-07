package com.yszn.security.rbac.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_acl")
public class SysAcl implements Serializable{

    private static final long serialVersionUID = -3325414518283221379L;

    /**
     * 权限id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 权限码
     */
    private String code;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限所在的权限模块id
     */
    @Column(name = "acl_module_id")
    private Long aclModuleId;

    /**
     * 请求的url, 可以填正则表达式
     */
    private String url;

    /**
     * Vue组件路径
     */
    private String path;

    /**
     * 是否需要用户绑定手机才可以访问，1：是，0：不是
     */
    @Column(name = "bind_phone")
    private Integer bindPhone;

    /**
     * Vue组件名称
     */
    private String component;

    /**
     * 类型，1：菜单，2：按钮，3：其他
     */
    private Integer type;

    /**
     * 状态，1：正常，0：冻结
     */
    private Integer status;

    /**
     * 权限在当前模块下的顺序，由小到大
     */
    private Integer seq;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 最后一次更新时间
     */
    @Column(name = "operate_time")
    private Date operateTime;

    /**
     * 最后一个更新者的ip地址
     */
    @Column(name = "operate_ip")
    private String operateIp;

    /**
     * 获取权限id
     *
     * @return id - 权限id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置权限id
     *
     * @param id 权限id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取权限码
     *
     * @return code - 权限码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置权限码
     *
     * @param code 权限码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取权限名称
     *
     * @return name - 权限名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置权限名称
     *
     * @param name 权限名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取权限所在的权限模块id
     *
     * @return acl_module_id - 权限所在的权限模块id
     */
    public Long getAclModuleId() {
        return aclModuleId;
    }

    /**
     * 设置权限所在的权限模块id
     *
     * @param aclModuleId 权限所在的权限模块id
     */
    public void setAclModuleId(Long aclModuleId) {
        this.aclModuleId = aclModuleId;
    }

    /**
     * 获取请求的url, 可以填正则表达式
     *
     * @return url - 请求的url, 可以填正则表达式
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置请求的url, 可以填正则表达式
     *
     * @param url 请求的url, 可以填正则表达式
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取Vue组件路径
     *
     * @return path - Vue组件路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置Vue组件路径
     *
     * @param path Vue组件路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取是否需要用户绑定手机才可以访问，1：是，0：不是
     *
     * @return bind_phone - 是否需要用户绑定手机才可以访问，1：是，0：不是
     */
    public Integer getBindPhone() {
        return bindPhone;
    }

    /**
     * 设置是否需要用户绑定手机才可以访问，1：是，0：不是
     *
     * @param bindPhone 是否需要用户绑定手机才可以访问，1：是，0：不是
     */
    public void setBindPhone(Integer bindPhone) {
        this.bindPhone = bindPhone;
    }

    /**
     * 获取Vue组件名称
     *
     * @return component - Vue组件名称
     */
    public String getComponent() {
        return component;
    }

    /**
     * 设置Vue组件名称
     *
     * @param component Vue组件名称
     */
    public void setComponent(String component) {
        this.component = component;
    }

    /**
     * 获取类型，1：菜单，2：按钮，3：其他
     *
     * @return type - 类型，1：菜单，2：按钮，3：其他
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型，1：菜单，2：按钮，3：其他
     *
     * @param type 类型，1：菜单，2：按钮，3：其他
     */
    public void setType(Integer type) {
        this.type = type;
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
     * 获取权限在当前模块下的顺序，由小到大
     *
     * @return seq - 权限在当前模块下的顺序，由小到大
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置权限在当前模块下的顺序，由小到大
     *
     * @param seq 权限在当前模块下的顺序，由小到大
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
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
     * 获取最后一次更新时间
     *
     * @return operate_time - 最后一次更新时间
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * 设置最后一次更新时间
     *
     * @param operateTime 最后一次更新时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取最后一个更新者的ip地址
     *
     * @return operate_ip - 最后一个更新者的ip地址
     */
    public String getOperateIp() {
        return operateIp;
    }

    /**
     * 设置最后一个更新者的ip地址
     *
     * @param operateIp 最后一个更新者的ip地址
     */
    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }
}