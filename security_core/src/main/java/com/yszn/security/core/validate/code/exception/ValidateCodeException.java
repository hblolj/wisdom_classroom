package com.yszn.security.core.validate.code.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: hblolj
 * @Date: Create in 14:37 2017/10/30
 * AuthenticationException
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
