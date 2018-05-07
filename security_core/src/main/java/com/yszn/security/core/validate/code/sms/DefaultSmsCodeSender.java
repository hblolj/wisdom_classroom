package com.yszn.security.core.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: hblolj
 * @Date: 2018/5/3 15:54
 * @Description:
 * @Version: 1.0
 **/
public class DefaultSmsCodeSender implements SmsCodeSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @param mobile 手机号
     * @param code 验证码
     * @return
     */
    @Override
    public Boolean send(String mobile, String code) {
        logger.info("默认的短信验证码发送器，暂未配置正式短信验证码发送器!");
        logger.info("给" + mobile + "发送的验证码是: " + code);
        return true;
    }
}
