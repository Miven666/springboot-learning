package com.miven.springboot.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by mingzhi.xie on 2018/9/27.
 */
@ComponentScan  // 开启组件扫描
@EnableAutoConfiguration    // 开启自动配置
/**
 * @EnableAutoConfiguration
 * 这个注释告诉Spring Boot根据你添加的jar依赖关系“猜测”你想要如何配置Spring
 * 如我们在pom.xml中添加了spring-boot-starter-web依赖
 * 它就会假定我们正在开发Web应用程序并相应地设置Spring
 * 虽然springboot很会“猜测”，但是总有猜不中我们心思的时候，比如我们的web项目需要支持跨域访问
 * 自然而然我们就需要做相应的配置
 */
public class Example {

    public static void main(String[] args) {
        SpringApplication.run(Example.class,args);
    }
}
