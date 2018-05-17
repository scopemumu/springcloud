package com.estone.erp.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * logstash日志类，ELK日志记录唯一入口
 * 
 * @author Kevin
 *
 */
public class Logstash {
    private static final Logger logger = LoggerFactory.getLogger(Logstash.class);

    /**
     * info level
     * 
     * @param clazz class类型
     * @param format 格式
     * @param args 参数
     */
    public static void info(Class<?> clazz, String format, Object... args) {
        logger.info("{}-->" + format, clazz, args);
    }

    /**
     * warn level
     * 
     * @param clazz class类型
     * @param format 格式
     * @param args 参数
     */
    public static void warn(Class<?> clazz, String format, Object... args) {
        logger.warn("{}-->" + format, clazz, args);
    }

    /**
     * warn level
     * 
     * @param message 消息
     * @param t 异常
     */
    public static void warn(String message, Throwable t) {
        logger.warn(message, t);
    }

    /**
     * error level
     * 
     * @param clazz class类型
     * @param format 格式
     * @param args 参数
     */
    public static void error(Class<?> clazz, String format, Object... args) {
        logger.error("{}-->" + format, clazz, args);
    }

    /**
     * error level
     * 
     * @param message 消息
     * @param t 异常
     */
    public static void error(String message, Throwable t) {
        logger.error(message, t);
    }
}
