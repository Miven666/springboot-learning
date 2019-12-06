package com.miven;

import com.miven.spring.context.XmzComponentScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *  演示样例启动器
 * @author mingzhi.xie
 * @date 2018/9/27
 */
@SpringBootApplication
@XmzComponentScan
public class DemoLauncher {

    public static void main(String[] args) {
        SpringApplication.run(DemoLauncher.class,args);
    }

    @Value("${project.version}")
    private String version;

    @Bean
    ApplicationRunner version() {
        return args -> System.out.println(version);
    }
}
