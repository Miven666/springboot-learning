package com.miven;

import com.miven.spring.context.XmzComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 *  演示样例启动器
 * @author mingzhi.xie
 * @date 2018/9/27
 */
@ComponentScan
@EnableAutoConfiguration
@SpringBootConfiguration
@XmzComponentScan
public class DemoLauncher {

    public static void main(String[] args) {
        SpringApplication.run(DemoLauncher.class,args);
    }
}
