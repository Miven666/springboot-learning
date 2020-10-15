package com.miven.springboot.prometheus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mingzhi.xie
 * @since 2020/10/14 1.0
 */
@SpringBootApplication
public class PrometheusLauncher {

    public static void main(String[] args) {
        SpringApplication.run(PrometheusLauncher.class, args);
    }
}
