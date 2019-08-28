package com.miven.springboot.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 *  启动器
 * @author mingzhi.xie
 * @date 2019/8/28
 * @since 1.0
 */
@EnableCaching
@SpringBootApplication
public class CacheLauncher {

    public static void main(String[] args) {
        SpringApplication.run(CacheLauncher.class, args);
    }
}
