package com.yszn.security.core.validate.code;

import com.yszn.security.core.validate.code.exception.ValidateCodeException;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: hblolj
 * @Date: Create in 16:41 2017/11/8
 * @Description: 验证码处理器，封装不同校验码的处理器
 */
public interface ValidateCodeProcessor {

    /**
     * 创建验证码
     * @param request
     */
    Boolean create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     * @param request
     */
    void validate(ServletWebRequest request) throws ValidateCodeException;
}
