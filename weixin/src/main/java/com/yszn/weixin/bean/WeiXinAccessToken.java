package com.yszn.weixin.bean;

public class WeiXinAccessToken {
    /**
     * 获取调用接口令牌
     */
    private String token;
    /**
     * 令牌过期时间，一般为2000mm
     */
    private int expiresIn;
    /**
     * 获取到的凭证
     */
    private String access_token;
    /**
     * 凭证的有效时间，单位秒
     */
    private String expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
