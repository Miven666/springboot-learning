package com.miven.springboot.transaction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 启动器
 * @author mingzhi.xie
 * @date 2019/12/12
 * @since 1.0
 */
@SpringBootApplication
@MapperScan("com.miven.springboot.transaction.mapper")
public class TransactionLauncher {
    public static void main(String[] args) {
        SpringApplication.run(TransactionLauncher.class, args);
    }

    @Bean
    ApplicationRunner runner(TeacherService teacherService) {
        return args -> teacherService.update(1, 33, "张三");
    }
}