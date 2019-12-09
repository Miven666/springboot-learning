package com.miven.springboot.jta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动器
 * @author mingzhi.xie
 * @date 2019/12/9
 * @since 1.0
 */
@SpringBootApplication
public class JtaLauncher {

    public static void main(String[] args) {
        SpringApplication.run(JtaLauncher.class, args);
    }
}
