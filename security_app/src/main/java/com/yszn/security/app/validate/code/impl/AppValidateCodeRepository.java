package com.yszn.security.app.validate.code.impl;

import com.yszn.security.core.properties.base.SecurityProperties;
import com.yszn.security.core.validate.code.ValidateCodeRepository;
import com.yszn.security.core.validate.code.ValidateCodeType;
import com.yszn.security.core.validate.code.base.ValidateCode;
import com.yszn.security.core.validate.code.exception.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * @author: hblolj
 * @Date: 2018/5/4 14:12
 * @Description:
 * @Version: 1.0
 **/
@Component
public class AppValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        redisTemplate.opsForValue().set(buildKey(request, validateCodeType), code,
                securityProperties.getCode().getSms().getExpireIn(), TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        Object value = redisTemplate.opsForValue().get(buildKey(request, validateCodeType));
        if (value == null){
            return null;
        }
        return (ValidateCode) value;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        redisTemplate.delete(buildKey(request, validateCodeType));
    }

    private String buildKey(ServletWebRequest request, ValidateCodeType type){
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)){
            throw new ValidateCodeException("请在请求头中携带deviceId参数");
        }
        return "code:" + type.toString().toLowerCase() + ":" + deviceId;
    }
}
