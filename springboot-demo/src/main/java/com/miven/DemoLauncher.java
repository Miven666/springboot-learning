package com.miven;

import com.miven.spring.context.XmzComponentScan;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.validation.spi.ValidationProvider;

/**
 *  演示样例启动器
 * @author mingzhi.xie
 * @date 2018/9/27
 */
@SpringBootApplication
@XmzComponentScan
public class DemoLauncher {

    public static void main(String[] args) {
        ClassLoader classLoader = Object.class.getClassLoader();
        System.out.println(classLoader);

        ClassLoader loader = DemoLauncher.class.getClassLoader();
        System.out.println(loader.getClass().getName());
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader.getClass().getName());

        ClassLoader val = ValidationProvider.class.getClassLoader();
        System.out.println("ValidationProvider -> " + val.getClass().getName());

        ClassLoader hi = HibernateValidator.class.getClassLoader();
        System.out.println("HibernateValidator -> " + hi.getClass().getName());

        SpringApplication.run(DemoLauncher.class,args);
    }

    @Value("${project.version}")
    private String version;

    @Bean
    ApplicationRunner version() {
        return args -> System.out.println(version);
    }
}