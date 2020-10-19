package com.miven.springboot.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动器
 *
 * @author mingzhi.xie
 * @since 2020/10/15 1.0
 */
@SpringBootApplication
public class ElasticsearchLauncher {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchLauncher.class, args);
    }
}
