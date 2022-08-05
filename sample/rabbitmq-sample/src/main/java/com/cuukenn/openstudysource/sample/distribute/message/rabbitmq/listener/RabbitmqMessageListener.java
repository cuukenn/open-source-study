package com.cuukenn.openstudysource.sample.distribute.message.rabbitmq.listener;

import com.cuukenn.openstudysource.sample.distribute.message.rabbitmq.pojo.SimpleMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changgg
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class RabbitmqMessageListener {
    public static final String MESSAGE_EXCHANGE = "message-exchange";
    public static final String MESSAGE_QUEUE = "message-queue";


    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    FanoutExchange exchange() {
        return ExchangeBuilder.fanoutExchange(MESSAGE_EXCHANGE).build();
    }

    @Bean
    Queue messageQueue() {
        return new Queue(MESSAGE_QUEUE);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(messageQueue()).to(exchange());
    }

    //region 相同交换机相同队列(点对点)

    /**
     * 消费者
     *
     * @param message 消息
     */
    @RabbitListener(queues = MESSAGE_QUEUE)
    public void messageConsumer1(SimpleMessage message) {
        log.info("revive message(same queue 1) ===> {}", message);
    }

    /**
     * 消费者
     *
     * @param message 消息
     */
    @RabbitListener(queues = MESSAGE_QUEUE)
    public void messageConsumer2(SimpleMessage message) {
        log.info("revive message(same queue 2) ===> {}", message);
    }
    //endregion

    //region 相同交换机不同队列(发布订阅)

    /**
     * 消费者
     *
     * @param message 消息
     */
    @RabbitListener(
            bindings = @QueueBinding(exchange = @Exchange(value = MESSAGE_EXCHANGE, type = ExchangeTypes.FANOUT),
                    value = @org.springframework.amqp.rabbit.annotation.Queue(MESSAGE_QUEUE + "-2")
            )
    )
    public void messageConsumer3(SimpleMessage message) {
        log.info("revive message(diff queue 1) ===> {}", message);
    }

    /**
     * 消费者
     *
     * @param message 消息
     */
    @RabbitListener(
            bindings = @QueueBinding(exchange = @Exchange(value = MESSAGE_EXCHANGE, type = ExchangeTypes.FANOUT),
                    value = @org.springframework.amqp.rabbit.annotation.Queue(MESSAGE_QUEUE + "-3")
            )
    )
    public void messageConsumer4(SimpleMessage message) {
        log.info("revive message(diff queue 2) ===> {}", message);
    }
    //endregion
}
