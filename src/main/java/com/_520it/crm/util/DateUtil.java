package com._520it.crm.util;

import java.util.Calendar;
import java.util.Date;
public class DateUtil {
    public static Date getBeginDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //设置时
        c.set(Calendar.HOUR_OF_DAY, 0);
        //设置分
        c.set(Calendar.MINUTE, 0);
        //设置秒
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Date getEndDate(Date date) {
        date = getBeginDate(date);
        Calendar c = Calendar.getInstance();
        //设置时间
        c.setTime(date);
        //天数加一
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.SECOND, -1);
        return c.getTime();
    }
}