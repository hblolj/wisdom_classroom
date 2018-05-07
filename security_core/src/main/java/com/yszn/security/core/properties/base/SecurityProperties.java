package com.yszn.security.core.properties.base;

import com.yszn.security.core.properties.app.OAuth2Properties;
import com.yszn.security.core.properties.browser.BrowserProperties;
import com.yszn.security.core.properties.code.ValidateCodeProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: hblolj
 * @Date: 2018/5/2 9:44
 * @Description: 权限配置
 * @Version: 1.0
 **/
@Component
@ConfigurationProperties(prefix = "yszn.security")
public class SecurityProperties {

    /**
     * 浏览器环境配置
     */
    private BrowserProperties browser = new BrowserProperties();
    /**
     * 验证码环境配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();
    /**
     * App OAuth 设置
     */
    private OAuth2Properties oauth2 = new OAuth2Properties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public OAuth2Properties getOauth2() {
        return oauth2;
    }

    public void setOauth2(OAuth2Properties oauth2) {
        this.oauth2 = oauth2;
    }
}
