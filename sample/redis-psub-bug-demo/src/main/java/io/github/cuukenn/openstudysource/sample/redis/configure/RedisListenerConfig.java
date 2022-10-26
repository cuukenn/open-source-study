package io.github.cuukenn.openstudysource.sample.redis.configure;

import cn.hutool.core.thread.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.concurrent.Executors;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@Slf4j
public class RedisListenerConfig {
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setErrorHandler(throwable -> {
            log.error("error happened,msg:{}", throwable.getMessage());
        });
        container.setTaskExecutor(Executors.newFixedThreadPool(5, new NamedThreadFactory("redis-task-", false)));
        container.setSubscriptionExecutor(Executors.newFixedThreadPool(5, new NamedThreadFactory("redis-sub-", false)));
        return container;
    }
}
