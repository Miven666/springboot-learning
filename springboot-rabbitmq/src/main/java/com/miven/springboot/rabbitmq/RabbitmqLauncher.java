package com.miven.springboot.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  启动器
 * @author mingzhi.xie
 * @date 2019/8/26
 * @since 1.0
 */
@SpringBootApplication
public class RabbitmqLauncher {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqLauncher.class, args).close();
    }
}
