package com.miven.springboot.cache;

import com.alibaba.fastjson.JSON;
import com.miven.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mingzhi.xie
 * @date 2019/8/28
 * @since 1.0
 */
@Slf4j
@Configuration
public class CacheConfiguration {

    @Bean
    public CommandLineRunner runner(BookRepository bookRepository) {
        return args -> {
            log.info(".... Fetching One Book");
            long one = System.currentTimeMillis();
            bookRepository.getBook("isbn-1234", "One Book");
            long  intervalOne = System.currentTimeMillis() - one;
            log.info("第一次获取 isbn-1234，估计耗时 " + intervalOne + " 秒");
            long two = System.currentTimeMillis();
            bookRepository.getBook("isbn-1234", "One Book");
            long  intervalTwo = System.currentTimeMillis() - two;
            log.info("第二次获取 isbn-1234，估计耗时 " + intervalTwo + " 秒");

            log.info(".... Putting One Book");
            Book bookUpdate = bookRepository.putBook("isbn-1234", "更新后 book");
            log.info("根据 isbn-1234 将 One Book 更新为 {}", JSON.toJSONString(bookUpdate));
            long three = System.currentTimeMillis();
            Book book = bookRepository.getBook("isbn-1234", "One Book");
            long  intervalThree= System.currentTimeMillis() - three;
            log.info("更新后获取 isbn-1234，估计耗时 " + intervalThree + " 秒，获取到的 book {}", JSON.toJSONString(book));

            log.info(".... Removing Some Book");
            bookRepository.removeBook("isbn-1234", "将 Book 删除");
            long four = System.currentTimeMillis();
            Book bookDelete = bookRepository.getBook("isbn-1234", "删除后的 book");
            long  intervalFour= System.currentTimeMillis() - four;
            log.info("删除后获取 isbn-1234，估计耗时 " + intervalFour + " 秒，获取到的 book {}", JSON.toJSONString(bookDelete));
        };
    }
}
