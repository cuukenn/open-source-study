package com.cuukenn.opensource.redis.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author changgg
 */
@Component
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class RedisSchedule {
    private final StringRedisTemplate stringRedisTemplate;

    @Scheduled(fixedDelay = 5 * 1000)
    public void addKey() {
        final String key = UUID.randomUUID().toString().replace("-", "");
        log.info("add message,key:{}", key);
        stringRedisTemplate.opsForValue().set(key, "1", 5, TimeUnit.SECONDS);
    }
}
