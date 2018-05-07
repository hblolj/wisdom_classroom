package com.yszn.security.core.validate.code.sms;

import com.yszn.security.core.properties.base.SecurityProperties;
import com.yszn.security.core.validate.code.ValidateCodeGenerator;
import com.yszn.security.core.validate.code.base.ValidateCode;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: hblolj
 * @Date: 2018/5/3 15:50
 * @Description: 短信验证码生成器
 * @Version: 1.0
 **/
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator{

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generator(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }
}
