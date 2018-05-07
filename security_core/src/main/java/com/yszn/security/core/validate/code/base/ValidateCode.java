package com.yszn.security.core.validate.code.base;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: hblolj
 * @Date: 2018/5/3 14:41
 * @Description: 验证码基础信息
 * @Version: 1.0
 **/
public class ValidateCode implements Serializable{

    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * @param code
     * @param expireTime 过期时间
     */
    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    /***
     * @param code
     * @param expireIn 有效时长 单位:分钟
     */
    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusMinutes(expireIn);
    }

    /**
     * 是否过期
     * @return
     */
    public boolean isExpired(){return LocalDateTime.now().isAfter(expireTime);}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
