package com.yszn.security.core.properties.app;

/**
 * @author: hblolj
 * @Date: 2018/5/4 13:40
 * @Description:
 * @Version: 1.0
 **/
public class OAuth2Properties {

    private OAuth2ClientProperties[] clients = {};

    private String jwtSigningKey = "ori";

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }
}
