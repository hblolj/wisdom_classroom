package com.yszn.security.core.handler;

import com.yszn.security.core.support.JsonData;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: hblolj
 * @Date: 2018/5/7 9:43
 * @Description: 全局异常处理
 * @Version: 1.0
 **/
@ControllerAdvice
public class RestExceptionHandler {

    //验收异常
    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseBody
    public JsonData<String> checkAcceptExceptionHandler(InsufficientAuthenticationException inException){
        inException.printStackTrace();
        return new JsonData<>(1024, inException.getMessage(), "Token 超时失效");
//        return ReturnFormat.retParam(1018, null, checkAcceptException.getMessage());
    }
}
