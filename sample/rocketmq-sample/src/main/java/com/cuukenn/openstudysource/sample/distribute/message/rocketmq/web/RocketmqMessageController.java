package com.cuukenn.openstudysource.sample.distribute.message.rocketmq.web;

import cn.hutool.json.JSONUtil;
import com.cuukenn.openstudysource.sample.distribute.message.rocketmq.listener.RocketmqMessageListener;
import com.cuukenn.openstudysource.sample.distribute.message.rocketmq.pojo.SimpleMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author changgg
 */
@RestController
@RequestMapping("/api/message/rocketmq")
@RequiredArgsConstructor
public class RocketmqMessageController {
    private final RocketMQTemplate rocketMQTemplate;
    private final ObjectMapper objectMapper;
    private final RocketmqMessageListener rocketmqMessageListener;

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
        simpleMessage.setToken(rocketmqMessageListener.createToken());
        rocketMQTemplate.convertAndSend(RocketmqMessageListener.MESSAGE_TOPIC, JSONUtil.toJsonStr(simpleMessage));
        return "ok";
    }
}
