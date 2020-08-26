package cn.king.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author: wjl@king.cn
 * @time: 2020/4/12 22:19
 * @version: 1.0.0
 * @description: 日期时间工具类
 */
@Slf4j
public class DateTimeUtils {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private DateTimeUtils() {
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/12 22:37
     * @param: millisecond
     * @return: java.lang.String
     * @description: 毫秒转时间日期字符串, 返回 yyyy-MM-dd HH:mm:ss 格式
     */
    public static String getDateTime(Long millisecond) {
        Date date = new Date(millisecond);
        return getDateTime(date);
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/12 22:38
     * @param: millisecond
     * @return: java.lang.String
     * @description: 毫秒转时间字符串, 返回 yyyy-MM-dd 格式
     */
    public static String getDate(Long millisecond) {
        Date date = new Date(millisecond);
        return getDate(date);
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/12 22:16
     * @param:
     * @return: java.lang.String
     * @description: 获取当前时间的字符串形式, 返回 yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime() {
        return getDateTime(new Date());
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/12 22:27
     * @param:
     * @return: java.lang.String
     * @description: 获取当前时间的字符串形式, 返回 yyyy-MM-dd
     */
    public static String getDate() {
        return getDate(new Date());
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/12 22:16
     * @param: date
     * @return: java.lang.String
     * @description: 日期转字符串, 返回 yyyy-MM-dd HH:mm:ss 形式
     */
    public static String getDateTime(Date date) {
        return getDateOrDateTime(date, DATE_TIME_FORMAT);
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/12 22:26
     * @param: date
     * @return: java.lang.String
     * @description: 日期转字符串, 返回 yyyy-MM-dd 形式
     */
    public static String getDate(Date date) {
        return getDateOrDateTime(date, DATE_FORMAT);
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/12 22:30
     * @param: date
     * @return: java.util.Date
     * @description: 字符串转日期. 字符串必须满足 yyyy-MM-dd HH:mm:ss 格式
     */
    public static Date getDateTime(String date) {
        return getDateOrDateTime(date, DATE_TIME_FORMAT);
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/12 22:30
     * @param: date
     * @return: java.util.Date
     * @description: 字符串转日期. 字符串必须满足 yyyy-MM-dd 格式
     */
    public static Date getDate(String date) {
        return getDateOrDateTime(date, DATE_FORMAT);
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/5/1 14:31
     * @param: date
     * @return: java.time.LocalDateTime
     * @description: Date 转 LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/5/1 14:31
     * @param: localDateTime
     * @return: java.util.Date
     * @description: LocalDateTime 转 Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/8/26 11:21
     * @param: day
     * @return: long
     * @description: 获取当前时间+day天的毫秒值. day可以负数.
     */
    public static long getTimeMillis(int day) {
        LocalDateTime currentTime = new Date(System.currentTimeMillis()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return currentTime.minusDays(day).toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/12 22:24
     * @param: date
     * @param: format
     * @return: java.lang.String
     * @description: 日期转字符串. 字符串必须满足 format 格式
     */
    private static String getDateOrDateTime(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/12 22:16
     * @param: date
     * @return: java.util.Date
     * @description: 字符串转日期. 字符串必须满足 format 格式
     */
    private static Date getDateOrDateTime(String date, String format) {
        Date d = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            d = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            log.error("参数格式错误");
            e.printStackTrace();
        }
        return d;
    }

}
