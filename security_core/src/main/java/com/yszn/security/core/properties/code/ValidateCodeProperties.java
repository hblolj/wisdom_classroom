package com.yszn.security.core.properties.code;

/**
 * @author: hblolj
 * @Date: 2018/5/3 14:01
 * @Description: 验证码配置
 * @Version: 1.0
 **/
public class ValidateCodeProperties {

    /**
     * 图片验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();
    /**
     * 短信验证码配置
     */
    private SmsCodeProperties sms = new SmsCodeProperties();
    /**
     * 微信Code验证配置
     */
    private WxCodeProperties wx = new WxCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }

    public WxCodeProperties getWx() {
        return wx;
    }

    public void setWx(WxCodeProperties wx) {
        this.wx = wx;
    }
}
