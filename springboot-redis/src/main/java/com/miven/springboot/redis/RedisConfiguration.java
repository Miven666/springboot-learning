package com.miven.springboot.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miven.entity.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.CountDownLatch;

/**
 *  redis 配置类
 * @author mingzhi.xie
 * @date 2019/8/23
 * @since 1.0
 */
@Slf4j
@Configuration
@EnableCaching
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
    RedisMessageListenerContainer container(RedisConnectionFactory factory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
        return container;
    }

    @Bean
    CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.create(connectionFactory);
    }

    @Bean
    Jackson2JsonRedisSerializer<?> jacksonRedisSerializer() {
        Jackson2JsonRedisSerializer<?> jacksonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会抛出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        // 指定序列化映射范围（field、get、set），指定修饰符可见性ANY（private和public都包括）
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jacksonRedisSerializer.setObjectMapper(om);
        return jacksonRedisSerializer;
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory, Jackson2JsonRedisSerializer<?> jacksonRedisSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setDefaultSerializer(StringRedisSerializer.UTF_8);

        redisTemplate.setValueSerializer(jacksonRedisSerializer);
        redisTemplate.setHashValueSerializer(jacksonRedisSerializer);
        return redisTemplate;
    }

    @Bean
    CommandLineRunner runner(Operations operations, CountDownLatch latch, RedisConnectionFactory factory) {
        return args -> {
            Email email = new Email("xie6032mail@163.com", "Redis demo email!");
            operations.setEmail(email);
            operations.getEmail(email);
            operations.removeEmail(email);
            operations.getEmail(email);

            log.info("Sending message ...");
            new StringRedisTemplate(factory).convertAndSend("chat", "Hello from Redis!");
            latch.await();
        };
    }
}
