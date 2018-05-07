package com.yszn.security.core.validate.code;

import com.yszn.security.core.validate.code.base.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: hblolj
 * @Date: 2018/5/3 14:53
 * @Description: 验证码存取器，具体实现交给 App 和 Browser 模块
 * @Version: 1.0
 **/
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     * @param request
     * @param code
     * @param validateCodeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     * @param request
     * @param validateCodeType
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    /**
     * 移除验证码
     * @param request
     * @param validateCodeType
     */
    void remove(ServletWebRequest request, ValidateCodeType validateCodeType);
}
