package com.yszn.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.yszn.security.core.constant.SecurityConstants;
import com.yszn.security.core.constant.WeiXinConstants;
import com.yszn.security.core.properties.base.WeiXinProperties;
import com.yszn.weixin.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author: hblolj
 * @Date: 2018/5/7 16:36
 * @Description: 在微信公众号中用户进行认证
 * @Version: 1.0
 **/
@RestController
public class WeiXinAuthenticationController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WeiXinProperties weiXinProperties;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 发起身份认证
     * @param resp
     * @param url 回调路径
     */
    @GetMapping("/startWeiXinAuthentication")
    public void startWeiXinAuthentication(HttpServletResponse resp, @RequestParam(defaultValue = "") String url){
        //回调路径
        logger.info("需要回调的url: " + url);
        String backUrl = weiXinProperties.getCallBackServerAddress() + "/wxCallBack?url=" + url;
        String uri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + weiXinProperties.getAppId()
                + "&redirect_uri=" + URLEncoder.encode(backUrl)
                + "&response_type=code" +
                "&scope=snsapi_base" +
                "&state=STATE#wechat_redirect";
        try {
            resp.sendRedirect(uri);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 给微信的认证回调接口
     * @param req
     * @param resp
     * @param code 授权码
     * @param url 认证完成跳往的页面
     */
    @GetMapping("/wxCallBack")
    public void weiXinAuthenticationCallBack(HttpServletRequest req, HttpServletResponse resp, @RequestParam String code,
                                             @RequestParam String url) throws ServletException, IOException {
        String uri = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + weiXinProperties.getAppId()
                + "&secret=" + weiXinProperties.getAppSecret()
                + "&code=" + code
                + "&grant_type=authorization_code";
        String result = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String openid = jsonObject.getString("openid");
        String token = jsonObject.getString("access_token");
        String serverName = req.getServletContext().getContextPath();
        if (openid == null){
            logger.error("通过授权码获取到的结果: {}, 获取到的用户 OpenId 为 null", result);
            return;
        }
        // 判断用户是否关注微信公众号
        int follow = followYoN(openid);
        if(follow == 1){
            logger.info("OpenId: {} 已关注", openid);
            // TODO: 2018/5/7 使用 OpenId 进行认证流程
            req.getSession().setAttribute(WeiXinConstants.URL, url);
            req.getRequestDispatcher(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_WX + "?wxCode=" + openid)
                    .forward(req,resp);
        }else {
            logger.info("OpenId: {} 未关注", openid);
            // 未关注的，跳转到关注页面，提示用户关注公众号
            // TODO: 2018/5/7 关注页面动态配置
            resp.sendRedirect(serverName + "/html/follow.html");
        }
    }

    /**
     * 判断用户是否关注公众号
     * @param openid
     * @return
     * @throws IOException
     */
    private Integer followYoN(String openid){
        if (TokenUtils.getInstance().getACCESS_TOKEN().isEmpty()){
            TokenUtils.getInstance().getToken(weiXinProperties.getAppId(), weiXinProperties.getAppSecret());
        }
        String at = TokenUtils.getInstance().getACCESS_TOKEN();
        String followUrl="https://api.weixin.qq.com/cgi-bin/user/info?" +
                "access_token=" + at +
                "&openid=" + openid +
                "&lang=zh_CN";
        String result = restTemplate.getForObject(followUrl, String.class);
        logger.info("用户是否关注公众号: {}", result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer follow = jsonObject.getInteger("subscribe");
        return follow;
    }
}
