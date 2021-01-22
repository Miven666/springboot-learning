package com.miven.springboot.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Scheduled 示例启动器
 *
 * @author mingzhi.xie
 * @since 2020/12/17 1.1.0
 */
@EnableScheduling
@SpringBootApplication
public class ScheduledLauncher {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledLauncher.class, args);
    }
}
