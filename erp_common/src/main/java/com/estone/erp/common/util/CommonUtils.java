package com.estone.erp.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

/***
 * 公共工具类
 * 
 * @author Kevin
 *
 */
public class CommonUtils {

    /**
     * 批量分页处理集合
     * 
     * @param list 集合
     * @param batchSize 批量大小
     * @param consumer 处理函数
     */
    public static <E> void batchResolve(List<E> list, int batchSize, Consumer<List<E>> consumer) {
        if (CollectionUtils.isEmpty(list) || batchSize <= 0 || consumer == null) {
            return;
        }

        int size = list.size();
        if (size <= batchSize) {
            consumer.accept(list);
            return;
        }

        int page = (size - 1) / batchSize + 1;
        int start = 0;
        int end = 0;
        for (int i = 1; i <= page; i++) {
            end = start + (i == page ? (size - batchSize * (i - 1)) : batchSize);
            consumer.accept(list.subList(start, end));
            start = end;
        }
    }

    /**
     * 转换集合模型
     * 
     * @param list
     * @param resolve
     * @return
     */
    public static <T, R> List<R> transfer(List<T> list, Function<T, R> resolve) {
        if (CollectionUtils.isEmpty(list) || resolve == null) {
            return new ArrayList<>(0);
        }

        List<R> result = new ArrayList<>(list.size());
        for (T t : list) {
            result.add(resolve.apply(t));
        }

        return result;
    }

    /**
     * 通过stream-流方式转换集合模型
     * 
     * @param list
     * @param resolve
     * @return
     */
    public static <T, R> List<R> transferByStream(List<T> list, Function<T, R> resolve) {
        if (CollectionUtils.isEmpty(list) || resolve == null) {
            return new ArrayList<>(0);
        }

        List<R> result = list.stream().map(resolve).collect(Collectors.toList());
        return result;
    }
}
