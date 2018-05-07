package com.yszn.security.core.service.impl;

import com.yszn.security.core.service.IRedisService;
import org.springframework.stereotype.Service;

/**
 * @author: hblolj
 * @Date: 2018/5/3 21:02
 * @Description:
 * @Version: 1.0
 **/
@Service
public class RedisServiceImpl extends IRedisService<Object> {

    private static final String REDIS_KEY = "OBJECT_REDIS_KEY";

    @Override
    protected String getRedisKey() {
        return REDIS_KEY;
    }
}
