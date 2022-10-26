package io.github.cuukenn.openstudysource.sample.distribute.message.rabbitmq.web;

import io.github.cuukenn.openstudysource.sample.distribute.message.rabbitmq.pojo.SimpleMessage;
import io.github.cuukenn.openstudysource.sample.distribute.message.rabbitmq.listener.RabbitmqMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author changgg
 */
@RestController
@RequestMapping("/api/message/rabbitmq")
@RequiredArgsConstructor
public class RabbitmqMessageController {
    private final RabbitTemplate rabbitTemplate;

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
        rabbitTemplate.convertAndSend(RabbitmqMessageListener.MESSAGE_EXCHANGE, "", simpleMessage);
        return "ok";
    }
}
