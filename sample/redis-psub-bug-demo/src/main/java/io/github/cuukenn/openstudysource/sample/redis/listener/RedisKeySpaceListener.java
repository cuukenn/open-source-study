package io.github.cuukenn.openstudysource.sample.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyspaceEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @author changgg
 */
@Component
@Slf4j
public class RedisKeySpaceListener extends KeyspaceEventMessageListener {
    /**
     * Creates new {@link KeyspaceEventMessageListener}.
     *
     * @param listenerContainer must not be {@literal null}.
     */
    public RedisKeySpaceListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doHandleMessage(Message message) {
        log.info("receive message:{}", message);
    }
}
