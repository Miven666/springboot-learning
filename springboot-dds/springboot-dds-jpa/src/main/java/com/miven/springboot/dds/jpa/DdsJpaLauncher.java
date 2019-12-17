package com.miven.springboot.dds.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动器
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DdsJpaLauncher {

    public static void main(String[] args) {
        SpringApplication.run(DdsJpaLauncher.class, args);
    }
}