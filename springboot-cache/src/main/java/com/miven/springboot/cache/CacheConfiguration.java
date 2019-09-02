package com.miven.springboot.cache;

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
            log.info(".... Fetching Some Books");
            log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234", "Some Book"));
            log.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567", "Some Book"));
            log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234", "Some Book"));
            log.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567", "Some Book"));

            log.info(".... Fetching One Book");
            log.info("isbn-1234 -->" + bookRepository.getBook("isbn-1234", "One Book"));
            log.info("isbn-4567 -->" + bookRepository.getBook("isbn-4567", "One Book"));
            log.info("isbn-1234 -->" + bookRepository.getBook("isbn-1234", "One Book"));
            log.info("isbn-4567 -->" + bookRepository.getBook("isbn-4567", "One Book"));

            log.info(".... Putting One Book");
            log.info("isbn-1234 -->" + bookRepository.putBook("isbn-1234", "One Book"));
            log.info("isbn-4567 -->" + bookRepository.putBook("isbn-4567", "One Book"));
            log.info("isbn-1234 -->" + bookRepository.putBook("isbn-1234", "One Book"));
            log.info("isbn-4567 -->" + bookRepository.putBook("isbn-4567", "One Book"));

            log.info(".... Removing Some Book");
            bookRepository.removeBook("isbn-1234", "Some Book");
            bookRepository.removeBook("isbn-4567", "Some Book");
            log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234", "Some Book"));
            log.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567", "Some Book"));
        };
    }
}
