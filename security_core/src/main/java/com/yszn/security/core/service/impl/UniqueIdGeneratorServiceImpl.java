package com.yszn.security.core.service.impl;

import com.yszn.security.core.service.UniqueIdGeneratorService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * @author: hblolj
 * @Date: 2018/5/2 11:31
 * @Description: 全局唯一 Id 生成器
 * @Version: 1.0
 **/
@Service
public class UniqueIdGeneratorServiceImpl implements UniqueIdGeneratorService {

    @Autowired
    private RedisTemplate redisTemplate;

    private String pattern = "yyyyMMdd";

    @Override
    public Long fetchDailyUniqueId(String key, Integer length, Boolean haveDay) throws Exception {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        counter.getAndAdd(1);
        counter.expire(1, TimeUnit.DAYS);
        long num = counter.longValue();
        if (haveDay){
            return createUniqueId(num, length);
        }else {
            return num;
        }
    }

    @Override
    public Long fetchUniqueId(String key, Integer length, Boolean haveDay) throws Exception {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        counter.getAndAdd(1);
        long num = counter.longValue();
        if (haveDay){
            return createUniqueId(num, length);
        }else {
            return num;
        }
    }

    private Long createUniqueId(Long num, Integer length) {
        Calendar now = new GregorianCalendar();
        String day = DateFormatUtils.format(now.getTime(), pattern);
        String id = String.valueOf(num);
        if (id.length() < length) {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            nf.setMaximumIntegerDigits(length);
            nf.setMinimumIntegerDigits(length);
            id = nf.format(num);
        }
        return Long.parseLong(day + id);
    }
}
