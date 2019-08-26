package com.miven.springboot.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  启动器
 * @author mingzhi.xie
 * @date 2019/8/23
 * @since 1.0
 */
@Slf4j
@SpringBootApplication
public class RedisLauncher {

    public static void main(String[] args) {
        SpringApplication.run(RedisLauncher.class, args);
    }
}
