package com.yszn.security.core.validate.code.sms;

/**
 * @author: hblolj
 * @Date: 2018/5/3 15:53
 * @Description: 短信发送接口
 * @Version: 1.0
 **/
public interface SmsCodeSender {

    Boolean send(String mobile, String code);
}
