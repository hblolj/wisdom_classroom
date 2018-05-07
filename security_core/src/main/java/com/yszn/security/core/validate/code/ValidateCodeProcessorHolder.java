package com.yszn.security.core.validate.code;

import com.yszn.security.core.validate.code.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author: hblolj
 * @Date: 2018/5/3 14:53
 * @Description: 验证码处理器的管理器
 * @Version: 1.0
 **/
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    /**
     * 根据枚举类型获取验证码处理器
     * @param type
     * @return
     */
    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type){
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    /**
     * 根据字符串类型获取验证码处理器
     * @param type
     * @return
     */
    public ValidateCodeProcessor findValidateCodeProcessor(String type){
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null){
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }

}
