package edu.nju.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author Shenmiu
 * @date 2018/03/22
 * <p>
 * 时间有关的工具类
 */
public class LocalDateTimeUtil {

    private static DateTimeFormatter dtfTillMinute = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static DateTimeFormatter dtfTillSecond = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 返回当前时间，时间格式为yyyy-MM-dd HH:mm
     *
     * @return 当前时间，精确到分
     */
    public static LocalDateTime nowTillMinute() {
        return LocalDateTime.parse(dtfTillMinute.format(LocalDateTime.now()), dtfTillMinute);
    }

    /**
     * 返回当前时间，时间格式为yyyy-MM-dd HH:mm:ss
     *
     * @return 当前时间，精确到秒
     */
    public static LocalDateTime nowTillSecond() {
        return LocalDateTime.parse(dtfTillSecond.format(LocalDateTime.now()), dtfTillSecond);
    }

    /**
     * 格式化时间到分钟级别
     *
     * @param tillMinute 需要格式化的对象
     * @return 格式化好的时间
     */
    public static String formatTillMinute(LocalDateTime tillMinute) {
        return tillMinute.format(dtfTillMinute);
    }

    /**
     * 格式化时间到秒级别
     *
     * @param tillSecond 需要格式化的对象
     * @return 格式化好的时间
     */
    public static String formatTillSecond(LocalDateTime tillSecond) {
        return tillSecond.format(dtfTillSecond);
    }

    /**
     * 转化为毫秒数
     *
     * @param localDateTime 需要转化的时间
     * @return 毫秒数
     */
    public static Long toMillis(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }
}
