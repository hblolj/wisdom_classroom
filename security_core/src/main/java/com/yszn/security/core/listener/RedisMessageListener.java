package com.yszn.security.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author: hblolj
 * @Date: 2018/5/2 11:37
 * @Description:
 * @Version: 1.0
 **/
public class RedisMessageListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message message, byte[] bytes) {
        logger.info("Mesasge Receiver: " + message.toString());
    }
}
