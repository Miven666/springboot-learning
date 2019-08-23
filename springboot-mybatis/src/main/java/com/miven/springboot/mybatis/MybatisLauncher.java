package com.miven.springboot.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 *  启动器
 * @author mingzhi.xie
 * @date 2019/3/19
 */
@SpringBootApplication
@MapperScan(value = "com.miven.springboot.mybatis.repository.mapper")
public class MybatisLauncher {

    public static void main(String[] args) {
        SpringApplication.run(MybatisLauncher.class, args);
    }
}
