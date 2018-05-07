package com.yszn.security.core.util;

import java.util.Calendar;

/**
 * @author: hblolj
 * @Date: 2018/5/7 14:52
 * @Description:
 * @Version: 1.0
 **/
public class TimeUtils {

    /**
     * 获取当前时间到第二天凌晨的秒数
     * @return
     */
    public static Long getSecondsNextEarlyMorning(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        // 改成这样就好了
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Long seconds = (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
        return seconds.longValue();
    }
}
