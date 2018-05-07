package com.yszn.security.core.validate.code;

import com.yszn.security.core.properties.base.SecurityProperties;
import com.yszn.security.core.validate.code.image.ImageCodeGenerator;
import com.yszn.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.yszn.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: hblolj
 * @Date: 2018/5/3 16:40
 * @Description:
 * @Version: 1.0
 **/
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 图片验证码生成器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    /**
     * 设置当不存在短信发送器实现时的默认发送器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){return new DefaultSmsCodeSender();}
}
