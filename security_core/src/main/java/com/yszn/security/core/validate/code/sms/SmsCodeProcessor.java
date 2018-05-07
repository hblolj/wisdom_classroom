package com.yszn.security.core.validate.code.sms;

import com.yszn.security.core.constant.SecurityConstants;
import com.yszn.security.core.validate.code.base.ValidateCode;
import com.yszn.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: hblolj
 * @Date: 2018/5/3 14:40
 * @Description: 短信验证码处理器
 * @Version: 1.0
 **/
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode>{

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected Boolean send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        return smsCodeSender.send(mobile, validateCode.getCode());
    }
}
