package com.yszn.security.core.service;

import com.yszn.security.core.constant.CacheKeyConstants;

/**
 * @author: hblolj
 * @Date: 2018/5/7 11:24
 * @Description:
 * @Version: 1.0
 **/
public interface SysCacheService {

    String getFromCache(CacheKeyConstants prefix, String... keys);

    void saveCache(String toSavedValue, int timeoutSeconds, CacheKeyConstants prefix, String... keys);

    void removeCache(CacheKeyConstants prefix, String... keys);
}
