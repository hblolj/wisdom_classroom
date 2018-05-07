package com.yszn.security.core.service.impl;

import com.google.common.base.Joiner;
import com.yszn.security.core.constant.CacheKeyConstants;
import com.yszn.security.core.service.SysCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: hblolj
 * @Date: 2018/5/7 11:25
 * @Description:
 * @Version: 1.0
 **/
@Service
public class SysCacheServiceImpl implements SysCacheService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String getFromCache(CacheKeyConstants prefix, String... keys){
        String cacheKey = generateCacheKey(prefix, keys);
        try {
            return (String) redisTemplate.opsForValue().get(cacheKey);
        } catch (Exception e) {
            logger.error("get from cache exception, prefix:{}, keys:{}", prefix.name(), keys == null ? "" : keys, e);
            return null;
        }
    }

    @Override
    public void saveCache(String toSavedValue, int timeoutSeconds, CacheKeyConstants prefix, String... keys) {
        if (toSavedValue == null) {
            return;
        }
        try {
            String cacheKey = generateCacheKey(prefix, keys);
            redisTemplate.opsForValue().set(cacheKey, toSavedValue, timeoutSeconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("save cache exception, prefix:{}, keys:{}", prefix.name(), keys, e);
        }
    }

    @Override
    public void removeCache(CacheKeyConstants prefix, String... keys) {
        String cacheKey = generateCacheKey(prefix, keys);
        redisTemplate.delete(cacheKey);
    }

    private String generateCacheKey(CacheKeyConstants prefix, String... keys) {
        String key = prefix.name();
        if (keys != null && keys.length > 0) {
            key += "_" + Joiner.on("_").join(keys);
        }
        logger.info("CacheKey:{}", key);
        return key;
    }
}
