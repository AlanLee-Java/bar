package com.ruoyi.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * Java 8 日期时间 API LocalDateTime工具类
 * </p>
 *
 * @author ALanLee
 * @date 2022-08-24
 */
public class LocalDateTimeUtils {

    /**
     * 私有化构造函数，避免创建对象
     */
    private LocalDateTimeUtils() {
    }

    /**
     * 获取指定时间的指定格式，返回String类型
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String toString(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取指定时间的指定格式，返回LocalDateTime类型
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static LocalDateTime toLocalDateTime(String localDateTime, String pattern) {
        return LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern(pattern));
    }

}