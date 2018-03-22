package edu.nju.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Shenmiu
 * @date 2018/03/22
 *
 * 时间有关的工具类
 */
public class LocalDateTimeUtil {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * 返回当前时间，时间格式为yyyy-MM-dd HH:mm
     * @return 当前时间，精确到分
     */
    public static LocalDateTime now(){
        return LocalDateTime.parse(dateTimeFormatter.format(LocalDateTime.now()), dateTimeFormatter);
    }

}
