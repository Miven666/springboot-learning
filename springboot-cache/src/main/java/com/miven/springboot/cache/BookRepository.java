package com.miven.springboot.cache;

import com.miven.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 *  书库
 * @author mingzhi.xie
 * @date 2019/8/28
 * @since 1.0
 */
@Slf4j
@Repository
public class BookRepository {

    @Cacheable(value = "some book", key = "#p0")
    public Book getByIsbn(String isbn, String title) {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        return  new Book(isbn, title);
    }

    @Cacheable(cacheNames = "one book", key = "#p0")
    public Book getBook(String isbn, String title) {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        return  new Book(isbn, title);
    }

    @CachePut(cacheNames = "one book", key = "#p0")
    public Book putBook(String isbn, String title) {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        return  new Book(isbn, title);
    }

    @CacheEvict(cacheNames = "some book", key = "#p0", allEntries = true, beforeInvocation = true)
    public void removeBook(String isbn, String title) {
        try {
            Thread.sleep(2000L);
            log.info("Removing " + title + " with isbn is "+ isbn);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
