package com.cuukenn.openstudysource.sample.distribute.message.rocketmq.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author changgg
 */
@Component
@Slf4j
@RequiredArgsConstructor
@RocketMQMessageListener(consumerGroup = "consumer-group", topic = RocketmqMessageListener.MESSAGE_TOPIC)
public class RocketmqMessageListener implements RocketMQListener<String> {
    public static final String MESSAGE_TOPIC = "message-topic";
    private final Set<String> tokens = new ConcurrentSkipListSet<>();

    /**
     * 创建令牌
     * token实现幂等
     *
     * @return 令牌
     */
    public String createToken() {
        final String token = UUID.randomUUID().toString();
        tokens.add(token);
        return token;
    }

    @Override
    public void onMessage(String message) {
        log.info("receive message ===> {}", message);
    }
}
