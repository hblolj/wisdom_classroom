package com.yszn.security.core.service;

/**
 * @author: hblolj
 * @Date: 2018/5/2 11:28
 * @Description: 全局唯一 Id 生成器
 * @Version: 1.0
 **/
public interface UniqueIdGeneratorService {

    /**
     * 每天从1开始生成唯一标识
     * @param key 要生成唯一标识的对象
     * @param length 要生成唯一标识的长度，不包括需要附加的时间戳 如果 haveDay 等于 false 或者 length 长度小于标识
     *               后缀的长度则无效
     * @param haveDay 是否要附加日期前缀
     * @return 唯一标识
     * @throws Exception
     */
    Long fetchDailyUniqueId(String key, Integer length, Boolean haveDay) throws Exception;


    /**
     * 全局从1开始生成唯一标识
     * @param key 要生成唯一标识的对象
     * @param length 要生成唯一标识的长度，不包括需要附加的时间戳 如果 haveDay 等于 false 或者 length 长度小于标识
     *               后缀的长度则无效
     * @param haveDay 是否要附加日期前缀
     * @return 唯一标识
     * @throws Exception
     */
    Long fetchUniqueId(String key, Integer length, Boolean haveDay) throws Exception;
}
