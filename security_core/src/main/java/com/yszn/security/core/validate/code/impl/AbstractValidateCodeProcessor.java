package com.yszn.security.core.validate.code.impl;

import com.yszn.security.core.validate.code.ValidateCodeGenerator;
import com.yszn.security.core.validate.code.ValidateCodeProcessor;
import com.yszn.security.core.validate.code.ValidateCodeRepository;
import com.yszn.security.core.validate.code.ValidateCodeType;
import com.yszn.security.core.validate.code.base.ValidateCode;
import com.yszn.security.core.validate.code.exception.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author: hblolj
 * @Date: 2018/5/3 14:41
 * @Description: 抽象验证码处理器
 * @Version: 1.0
 **/
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor{

    /**
     * 收集系统中所有的{@link ValidateCodeGenerator} 接口的实现
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    /**
     * 验证码存取器，在 App 与 Browser 有不同的实现
     */
    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    @Override
    public Boolean create(ServletWebRequest request) throws Exception {
        // 生成验证码
        C validateCode = generator(request);
        // 存储验证码
        save(request, validateCode);
        // 发送验证码
        return send(request, validateCode);
    }

    /**
     * 生成验证码
     * @param request
     * @return
     */
    private C generator(ServletWebRequest request) {
        // 根据类型找到对应类型的验证码处理器，然后调用它的验证码生成方法
        String type = getValidateCodeType(request).toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator generator = validateCodeGenerators.get(generatorName);
        if (generator == null){
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) generator.generator(request);
    }

    /**
     * 根据请求获取验证码的类型
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        //运行时实例类不是AbstractValidateCodeProcessor，而是具体的ImageCodeProcessor或者SmsCodeProcessor
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * 保存验证码
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode){
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        validateCodeRepository.save(request, code, getValidateCodeType(request));
    }

    /**
     * 发送验证码
     * @param request
     * @param validateCode
     * @return
     * @throws Exception
     */
    protected abstract Boolean send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 校验验证码
     * @param request
     * @throws ValidateCodeException
     */
    @Override
    public void validate(ServletWebRequest request) throws ValidateCodeException {
        ValidateCodeType validateCodeType = getValidateCodeType(request);
        // 拿到服务端存储的验证码
        C codeInServer = (C) validateCodeRepository.get(request, validateCodeType);

        String codeInRequest;

        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    validateCodeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            e.printStackTrace();
            throw new ValidateCodeException("获取验证码的值失败!");
        }

        if (StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException(validateCodeType + "验证码不能为空!");
        }

        if (codeInServer == null){
            throw new ValidateCodeException("验证码不存在!");
        }

        if (codeInServer.isExpired()){
            validateCodeRepository.remove(request, validateCodeType);
            throw new ValidateCodeException(validateCodeType + "验证码已过期!");
        }

        if (!StringUtils.equals(codeInServer.getCode(), codeInRequest)){
            throw new ValidateCodeException(validateCodeType + "验证码错误!");
        }

        //匹配成功，移除服务端存储的验证码
        validateCodeRepository.remove(request, validateCodeType);
    }
}
