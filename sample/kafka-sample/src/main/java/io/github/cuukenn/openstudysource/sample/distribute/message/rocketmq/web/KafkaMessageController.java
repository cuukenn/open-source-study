package io.github.cuukenn.openstudysource.sample.distribute.message.rocketmq.web;

import io.github.cuukenn.openstudysource.sample.distribute.message.rocketmq.listener.KafkaMessageListener;
import io.github.cuukenn.openstudysource.sample.distribute.message.rocketmq.pojo.SimpleMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author changgg
 */
@RestController
@RequestMapping("/api/message/kafka")
@RequiredArgsConstructor
public class KafkaMessageController {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final KafkaMessageListener kafkaMessageListener;

    /**
     * 发送消息至kafka
     * 此处使用令牌模式用于多consumer组进行幂等消费
     *
     * @return ok
     */
    @GetMapping("/send")
    @SneakyThrows
    public String sendMessage() {
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setCode(UUID.randomUUID().toString());
        simpleMessage.setMessage(UUID.randomUUID().toString());
        simpleMessage.setToken(kafkaMessageListener.createToken());
        kafkaTemplate.send(KafkaMessageListener.MESSAGE_TOPIC, objectMapper.writeValueAsString(simpleMessage));
        return "ok";
    }
}
