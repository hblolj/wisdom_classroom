package com.yszn.security.rbac.model;

import javax.persistence.*;

@Table(name = "sys_acl_data")
public class SysAclData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对应权限表主键
     */
    @Column(name = "acl_id")
    private Long aclId;

    /**
     * 状态，1：可用，0：不可用
     */
    private Byte status;

    /**
     * 参数
     */
    private String param;

    /**
     * 操作类型，0；等于，1：大于，2：小于，3：大于等于，4：小于等于，5：包含，6：介于之间，。。。
     */
    private Integer operation;

    private String value1;

    private String value2;

    /**
     * 后续有参数时连接的关系，0:没有其他参数控制，1：与&&，2：或||
     */
    @Column(name = "next_param_op")
    private Integer nextParamOp;

    /**
     * 顺序
     */
    private Byte seq;

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
     * 获取对应权限表主键
     *
     * @return acl_id - 对应权限表主键
     */
    public Long getAclId() {
        return aclId;
    }

    /**
     * 设置对应权限表主键
     *
     * @param aclId 对应权限表主键
     */
    public void setAclId(Long aclId) {
        this.aclId = aclId;
    }

    /**
     * 获取状态，1：可用，0：不可用
     *
     * @return status - 状态，1：可用，0：不可用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态，1：可用，0：不可用
     *
     * @param status 状态，1：可用，0：不可用
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取参数
     *
     * @return param - 参数
     */
    public String getParam() {
        return param;
    }

    /**
     * 设置参数
     *
     * @param param 参数
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * 获取操作类型，0；等于，1：大于，2：小于，3：大于等于，4：小于等于，5：包含，6：介于之间，。。。
     *
     * @return operation - 操作类型，0；等于，1：大于，2：小于，3：大于等于，4：小于等于，5：包含，6：介于之间，。。。
     */
    public Integer getOperation() {
        return operation;
    }

    /**
     * 设置操作类型，0；等于，1：大于，2：小于，3：大于等于，4：小于等于，5：包含，6：介于之间，。。。
     *
     * @param operation 操作类型，0；等于，1：大于，2：小于，3：大于等于，4：小于等于，5：包含，6：介于之间，。。。
     */
    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    /**
     * @return value1
     */
    public String getValue1() {
        return value1;
    }

    /**
     * @param value1
     */
    public void setValue1(String value1) {
        this.value1 = value1;
    }

    /**
     * @return value2
     */
    public String getValue2() {
        return value2;
    }

    /**
     * @param value2
     */
    public void setValue2(String value2) {
        this.value2 = value2;
    }

    /**
     * 获取后续有参数时连接的关系，0:没有其他参数控制，1：与&&，2：或||
     *
     * @return next_param_op - 后续有参数时连接的关系，0:没有其他参数控制，1：与&&，2：或||
     */
    public Integer getNextParamOp() {
        return nextParamOp;
    }

    /**
     * 设置后续有参数时连接的关系，0:没有其他参数控制，1：与&&，2：或||
     *
     * @param nextParamOp 后续有参数时连接的关系，0:没有其他参数控制，1：与&&，2：或||
     */
    public void setNextParamOp(Integer nextParamOp) {
        this.nextParamOp = nextParamOp;
    }

    /**
     * 获取顺序
     *
     * @return seq - 顺序
     */
    public Byte getSeq() {
        return seq;
    }

    /**
     * 设置顺序
     *
     * @param seq 顺序
     */
    public void setSeq(Byte seq) {
        this.seq = seq;
    }
}