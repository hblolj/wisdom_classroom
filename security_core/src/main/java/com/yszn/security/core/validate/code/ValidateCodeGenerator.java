package com.yszn.security.core.validate.code;

import com.yszn.security.core.validate.code.base.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: hblolj
 * @Date: 2018/5/3 14:49
 * @Description: 验证码生成器
 * @Version: 1.0
 **/
public interface ValidateCodeGenerator {

    /**
     * 生成校验码
     * @param request
     * @return
     */
    ValidateCode generator(ServletWebRequest request);
}
