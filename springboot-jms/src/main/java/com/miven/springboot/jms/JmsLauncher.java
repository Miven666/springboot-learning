package com.miven.springboot.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  启动器
 * @author mingzhi.xie
 * @date 2019/8/27
 * @since 1.0
 */
@SpringBootApplication
public class JmsLauncher {

    public static void main(String[] args) {
        SpringApplication.run(JmsLauncher.class, args);
    }
}
