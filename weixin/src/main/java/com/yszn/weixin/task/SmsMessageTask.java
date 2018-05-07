package com.yszn.weixin.task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yszn.security.core.constant.CacheKeyConstants;
import com.yszn.security.core.service.SysCacheService;
import com.yszn.security.core.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: hblolj
 * @Date: 2018/5/7 14:38
 * @Description: 短信任务类
 * @Version: 1.0
 **/
//@Component
public class SmsMessageTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysCacheService sysCacheService;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 刷新用户发送短信验证码次数
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void refreshPhoneSendSmsCount(){
        logger.info("refreshPhoneSendSmsCount");
        String fromCache = sysCacheService.getFromCache(CacheKeyConstants.PHONE_CAN_SEND_COUNT_IN_ONE_DAY);
        try {
            ConcurrentHashMap<String, Integer> map = objectMapper.readValue(fromCache, new TypeReference<ConcurrentHashMap<String, Integer>>() {});
            if (map == null){
                map = new ConcurrentHashMap<>(16);
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                entry.setValue(0);
            }
            sysCacheService.saveCache(objectMapper.writeValueAsString(map), TimeUtils.getSecondsNextEarlyMorning().intValue(),
                    CacheKeyConstants.PHONE_CAN_SEND_COUNT_IN_ONE_DAY);
        } catch (IOException e) {
            logger.info("重置短信验证码发送限制次数失败!", e);
        }
    }
}
