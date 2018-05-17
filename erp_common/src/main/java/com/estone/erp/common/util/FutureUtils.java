package com.estone.erp.common.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * Future工具类
 * 
 * @author Kevin
 *
 */
public class FutureUtils {
    private static final Logger logger = LoggerFactory.getLogger(FutureUtils.class);

    /**
     * 获取future的返回值，线程阻塞
     * 
     * @param future
     * @return T值
     */
    public static <T> T get(Future<T> future) {
        if (future != null) {
            try {
                return future.get();
            }
            catch (InterruptedException | ExecutionException e) {
                logger.error(e.getMessage(), e);
            }
        }

        return null;
    }
}
