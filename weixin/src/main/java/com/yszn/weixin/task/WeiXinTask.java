package com.yszn.weixin.task;

import com.yszn.security.core.constant.WeiXinConstants;
import com.yszn.security.core.properties.base.WeiXinProperties;
import com.yszn.weixin.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: hblolj
 * @Date: 2018/5/7 17:19
 * @Description: 微信定时任务类
 * @Version: 1.0
 **/
@Component
public class WeiXinTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WeiXinProperties weiXinProperties;

    /**
     * 刷新获取微信AccessToken
     */
    @Scheduled(cron = "0 55 * * * *")
    public void refreshAccessToken(){
        logger.info("refreshAccessToken");
        TokenUtils.getInstance().getToken(weiXinProperties.getAppId(), weiXinProperties.getAppSecret());
        TokenUtils.getInstance().getJSAPI_Ticket(weiXinProperties.getAppId(), weiXinProperties.getAppSecret());
        logger.info("刷新获取到的 AccessToken: {}" + TokenUtils.getInstance().getACCESS_TOKEN());
    }
}
