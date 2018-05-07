package com.yszn.security.core.constant;

/**
 * @author: hblolj
 * @Date: 2018/5/3 11:01
 * @Description:
 * @Version: 1.0
 **/
public interface SecurityConstants {

    /**
     * 默认的处理验证码的url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

    /**
     * 默认的手机验证码登录请求处理url
     * 手机短信登录url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/authentication/mobile";
    /**
     * 默认的用户名密码登录请求处理url
     * 表单登录url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/authentication/form";
    /**
     * 默认的微信 OpenId 登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_WX = "/authentication/wx";
    /**
     * 绑定手机号码请求处理url绑定手机号码请求处理url
     * 手机绑定url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_BIND_MOBILE = "/bindPhone";


    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     * 可能是短信登录，也可能短信绑定、解绑....
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    /**
     * 验证微信Code时，http请求中默认的携带微信信息的参数的名称
     * 微信登录时
     */
    String DEFAULT_PARAMETER_NAME_CODE_WX = "wxCode";
}
