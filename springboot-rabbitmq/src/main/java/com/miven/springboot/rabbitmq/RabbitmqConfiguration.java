package com.miven.springboot.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *  Rabbitmq 配置类
 * @author mingzhi.xie
 * @date 2019/8/26
 * @since 1.0
 */
@Slf4j
@Configuration
public class RabbitmqConfiguration {

    private static final String QUEUE_NAME = "spring-boot";
    private static final String TOPIC_EXCHANGE_NAME = "spring-boot-exchange";
    private static final String ROUTING_KEY = "routing.key.#";

    @Bean
    CountDownLatch downLatch() {
        return new CountDownLatch(1);
    }

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory factory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setMessageListener(listenerAdapter);
        container.setQueueNames(QUEUE_NAME);
        return container;
    }


    @Bean
    CommandLineRunner runner(CountDownLatch latch, RabbitTemplate rabbitTemplate) {
        return args -> {
            log.info("Sending message ...");
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, "routing.key.A", "Hello from RabbitMQ!");
            latch.await(10000, TimeUnit.MILLISECONDS);
            System.exit(0);
        };
    }
}
