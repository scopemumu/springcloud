package com.estone.erp.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import lombok.extern.slf4j.Slf4j;
import redis.clients.util.SafeEncoder;

@SuppressWarnings("unchecked")
@Component
@DependsOn("springUtils")
@Slf4j
public class RedisUtils {
    @Autowired
    public static RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public static void init() {
        redisTemplate = SpringUtils.getBean("redisTemplate", RedisTemplate.class);
        log.info("redis init completed.");
    }

    /**
     * list- 添加元素
     * 
     * @param key
     * @param value
     */
    public static void push(String key, Object... value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * list-获取元素
     * 
     * @param key
     * @return
     */
    public static <E> E pop(String key) {
        return (E) redisTemplate.opsForList().leftPop(key);
    }

    /**
     * list-获取大小
     * 
     * @param key
     * @return
     */
    public static Long llen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 删除keys
     * 
     * @param keys
     */
    public static void del(String... keys) {
        redisTemplate.delete(Arrays.asList(keys));
    }

    /**
     * 设置key value值
     * 
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取key对应的value
     * 
     * @param key
     * @return
     */
    public static <E> E get(String key) {
        return (E) redisTemplate.opsForValue().get(key);
    }

    /**
     * 批量设置key value值
     * 
     * @param datas
     */
    public static void batchSet(Map<String, Object> datas) {
        redisTemplate.executePipelined(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection conn) throws DataAccessException {
                conn.openPipeline();
                for (Map.Entry<String, Object> entry : datas.entrySet()) {
                    conn.set(SafeEncoder.encode(entry.getKey()), SerializationUtils.serialize(entry.getValue()));
                }
                conn.closePipeline();
                return null;
            }
        });
    }

    /**
     * 批量获取key value值
     * 
     * @param keys
     * @return
     */
    public static List<Object> batchGet(List<String> keys) {
        return redisTemplate.executePipelined(new RedisCallback<List<Object>>() {
            @Override
            public List<Object> doInRedis(RedisConnection conn) throws DataAccessException {
                conn.openPipeline();
                List<Object> list = new ArrayList<Object>(keys.size());
                for (String key : keys) {
                    list.add(SerializationUtils.deserialize(conn.get(SafeEncoder.encode(key))));
                }
                conn.closePipeline();
                return list;
            }
        });
    }

    /**
     * 是否存在key
     * 
     * @param key
     * @return
     */
    public static Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * set-获取大小
     * 
     * @param key
     * @return
     */
    public static Long scard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * set-添加元素
     * 
     * @param key
     * @param value
     * @return
     */
    public static Boolean sadd(String key, Object value) {
        Long result = redisTemplate.opsForSet().add(key, value);
        return result == 1 ? true : false;
    }

    /**
     * set-删除元素
     * 
     * @param key
     * @param memebers
     * @return
     */
    public static Boolean srem(String key, Object... memebers) {
        Long result = redisTemplate.opsForSet().remove(key, memebers);
        return result > 0 ? true : false;
    }

    /**
     * set-获取set集合元素
     * 
     * @param key
     * @return
     */
    public static <E> Set<E> smembers(String key) {
        return (Set<E>) redisTemplate.opsForSet().members(key);
    }
}
