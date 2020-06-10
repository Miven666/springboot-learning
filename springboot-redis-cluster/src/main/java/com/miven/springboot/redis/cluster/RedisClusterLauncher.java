package com.miven.springboot.redis.cluster;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author mingzhi.xie
 * @since 2020/6/10 1.0
 */
@SpringBootApplication
public class RedisClusterLauncher {

    public static void main(String[] args) {
        SpringApplication.run(RedisClusterLauncher.class, args);
    }

    @Bean
    public CommandLineRunner runner(StringRedisTemplate redisTemplate) {
        return args -> redisTemplate.opsForValue().set("detection", String.valueOf(System.currentTimeMillis()), 1, TimeUnit.HOURS);
    }
}
