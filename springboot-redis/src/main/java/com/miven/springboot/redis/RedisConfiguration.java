package com.miven.springboot.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 *  redis 配置类
 * @author mingzhi.xie
 * @date 2019/8/23
 * @since 1.0
 */
@Slf4j
@Configuration
public class RedisConfiguration {

    @Bean
    CountDownLatch downLatch() {
        return new CountDownLatch(1);
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory factory, MessageListenerAdapter adapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(adapter, new PatternTopic("chat"));
        return container;
    }

    @Bean
    CommandLineRunner runner(CountDownLatch latch, RedisConnectionFactory factory) {
        return args -> {
            log.info("Sending message ...");
            new StringRedisTemplate(factory).convertAndSend("chat", "Hello from Redis!");
            latch.await();
        };
    }
}
