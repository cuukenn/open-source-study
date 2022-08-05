package com.cuukenn.openstudysource.sample.distribute.message.rocketmq.listener;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;
import com.cuukenn.openstudysource.sample.distribute.message.rocketmq.pojo.SimpleMessage;

/**
 * kafka测试
 * 一个partition只能绑定一个消费组的一个消费者
 * partition & consumer-group & consumer
 * 1 & 1 & n
 * 1 & n & n（各自处理，数据冗余）
 * 1 & n & n（幂等处理，数据冗余）
 *
 * @author changgg
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaMessageListener {
    public static final String MESSAGE_TOPIC = "message-topic";
    private final Set<String> tokens = new ConcurrentSkipListSet<>();

    @Bean
    public NewTopic messageTopic() {
        log.info("create topic:{}", MESSAGE_TOPIC);
        return new NewTopic(MESSAGE_TOPIC, 1, (short) 1);
    }

    //region 同组消费者

    /**
     * 消费者
     *
     * @param record 数据
     */
    @KafkaListener(groupId = "test001", topics = MESSAGE_TOPIC)
    public void messageConsumer1(ConsumerRecord<String, String> record) {
        log.info("receive message 1(same group):{}", record.value());
    }

    /**
     * 消费者
     *
     * @param record 数据
     */
    @KafkaListener(groupId = "test001", topics = MESSAGE_TOPIC)
    public void messageConsumer2(ConsumerRecord<String, String> record) {
        log.info("receive message 2(same group):{}", record.value());
    }

    /**
     * 消费者
     *
     * @param record 数据
     */
    @KafkaListener(groupId = "test001", topics = MESSAGE_TOPIC)
    public void messageConsumer3(ConsumerRecord<String, String> record) {
        log.info("receive message 3(same group):{}", record.value());
    }
    //endregion

    //region 不同组消费者

    /**
     * 消费者
     *
     * @param record 数据
     */
    @KafkaListener(groupId = "test004", topics = MESSAGE_TOPIC)
    public void messageConsumer4(ConsumerRecord<String, String> record) {
        log.info("receive message 4(diff group):{}", record.value());
    }

    /**
     * 消费者
     *
     * @param record 数据
     */
    @KafkaListener(groupId = "test005", topics = MESSAGE_TOPIC)
    public void messageConsumer5(ConsumerRecord<String, String> record) {
        log.info("receive message 5(diff group):{}", record.value());
    }

    /**
     * 消费者
     *
     * @param record 数据
     */
    @KafkaListener(groupId = "test006", topics = MESSAGE_TOPIC)
    public void messageConsumer6(ConsumerRecord<String, String> record) {
        log.info("receive message 6(diff group):{}", record.value());
    }
    //endregion

    //region 多分组幂等,只一个成功消费

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

    /**
     * 消费者
     *
     * @param record 数据
     */
    @KafkaListener(groupId = "test007", topics = MESSAGE_TOPIC)
    public void messageConsumer7(ConsumerRecord<String, String> record) {
        SimpleMessage simpleMessage = JSONUtil.toBean(record.value(), SimpleMessage.class);
        if (!tokens.remove(simpleMessage.getToken())) {
            return;
        }
        log.info("receive message 7(diff group & idempotent with single pass):{}", record.value());
    }

    /**
     * 消费者
     *
     * @param record 数据
     */
    @KafkaListener(groupId = "test008", topics = MESSAGE_TOPIC)
    public void messageConsumer8(ConsumerRecord<String, String> record) {
        SimpleMessage simpleMessage = JSONUtil.toBean(record.value(), SimpleMessage.class);
        if (!tokens.remove(simpleMessage.getToken())) {
            return;
        }
        log.info("receive message 8(diff group & idempotent with single pass):{}", record.value());
    }

    /**
     * 消费者
     *
     * @param record 数据
     */
    @KafkaListener(groupId = "test009", topics = MESSAGE_TOPIC)
    public void messageConsumer9(ConsumerRecord<String, String> record) {
        SimpleMessage simpleMessage = JSONUtil.toBean(record.value(), SimpleMessage.class);
        if (!tokens.remove(simpleMessage.getToken())) {
            return;
        }
        log.info("receive message 9(diff group & idempotent with single pass):{}", record.value());
    }
    //endregion
}
