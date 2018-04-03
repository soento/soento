package com.soento.core.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yantao.zeng
 */
@Slf4j
@Component
public class RedisHandler {
    private static RedisTemplate redisTemplate;

    @Autowired
    public RedisHandler(RedisTemplate redisTemplate) {
        RedisHandler.redisTemplate = redisTemplate;
    }

    public static void set(Object key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static void set(Object key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public static Object get(Object k) {
        return redisTemplate.opsForValue().get(k);
    }

    public static void delete(Object key) {
        redisTemplate.delete(key);
    }
}
