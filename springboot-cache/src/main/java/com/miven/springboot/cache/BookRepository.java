package com.miven.springboot.cache;

import com.miven.entity.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 *  书库
 * @author mingzhi.xie
 * @date 2019/8/28
 * @since 1.0
 */
@Repository
public class BookRepository {

    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        return  new Book(isbn, "Some Book");
    }
}
