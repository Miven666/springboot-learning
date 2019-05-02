package com.miven.springboot.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author mingzhi.xie
 * @date 2018/9/27
 */
@ComponentScan
@EnableAutoConfiguration
@SpringBootConfiguration
public class SpringBootStart {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStart.class,args);
    }
}
