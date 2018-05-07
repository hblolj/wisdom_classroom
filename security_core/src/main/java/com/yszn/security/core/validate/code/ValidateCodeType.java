package com.yszn.security.core.validate.code;


import com.yszn.security.core.constant.SecurityConstants;

/**
 * @author: hblolj
 * @Date: Create in 19:03 2017/11/8
 * @Description: 校验码类型
 */
public enum ValidateCodeType {

    /**
     * 短信验证码
     */
    SMS{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    /**
     * 图片验证码
     */
    IMAGE{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    },
    /**
     * 微信 OpenId
     */
    WX{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_WX;
        }
    };

    /**
     * 校验时从请求中获取参数的名字
     * @return
     */
    public abstract String getParamNameOnValidate();
}
