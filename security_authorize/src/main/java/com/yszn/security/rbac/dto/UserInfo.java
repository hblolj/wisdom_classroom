package com.yszn.security.rbac.dto;

/**
 * @author: hblolj
 * @Date: 2018/5/2 10:50
 * @Description:
 * @Version: 1.0
 **/
public class UserInfo {

    /**
     * 用户Id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户手机号
     */
    private String telephone;
    /**
     * 用户邮箱
     */
    private String mail;
    /**
     * 用户所属组织
     */
    private Integer deptId;
    /**
     * 用户状态 1：正常，0：冻结状态，2：删除
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
