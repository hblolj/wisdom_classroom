package com.yszn.weixin.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yszn.weixin.bean.WeiXinAccessToken;
import com.yszn.weixin.bean.WeiXinJsApiTicket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

/**
 * @author: hblolj
 * @Date: 2018/5/7 17:00
 * @Description:
 * @Version: 1.0
 **/
public class TokenUtils {

    private static TokenUtils tokenUtils;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String ACCESS_TOKEN = "";

    private String JSAPI_TICKET = "";

    private RestTemplate restTemplate = new RestTemplate();

    private ObjectMapper objectMapper = new ObjectMapper();

    public static TokenUtils getInstance() {
        if (tokenUtils == null){
            synchronized (TokenUtils.class){
                if (tokenUtils == null){
                    tokenUtils = new TokenUtils();
                }
            }
        }
        return tokenUtils;
    }

    private TokenUtils() {
    }

    public Map<String, String> config(String url, String appId, String secret){
        //获取token
        if (ACCESS_TOKEN.isEmpty()){
            getToken(appId, secret);
        }
        if (JSAPI_TICKET.isEmpty()){
            //获取js api ticket
            getJSAPI_Ticket(appId, secret);
        }
        //sign
        Map<String, String> sign = SignUtils.sign(JSAPI_TICKET, url);
        return sign;
    }

    /**
     * 获取token
     * @return
     */
    public void getToken(String appId, String secret){
        String url = "https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential&appid=" + appId
                + "&secret=" + secret;
        String result = restTemplate.getForObject(url, String.class);
        logger.info("getToken Result: {}", result);
        try {
            WeiXinAccessToken accessToken = objectMapper.readValue(result, WeiXinAccessToken.class);
            ACCESS_TOKEN = accessToken.getAccess_token();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /***
     * 获取JS_API_TICKET
     */
    public void getJSAPI_Ticket(String appId, String secret){
        if (ACCESS_TOKEN.isEmpty()){
            getToken(appId, secret);
        }
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ ACCESS_TOKEN +"&type=jsapi";
        String result = restTemplate.getForObject(url, String.class);
        logger.info("JS API TICKET: {}", result);
        WeiXinJsApiTicket api_ticket = null;
        try {
            api_ticket = objectMapper.readValue(result, WeiXinJsApiTicket.class);
            JSAPI_TICKET = api_ticket.getTicket();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public String getACCESS_TOKEN() {
        return ACCESS_TOKEN;
    }

    public String getJSAPI_TICKET() {
        return JSAPI_TICKET;
    }
}
