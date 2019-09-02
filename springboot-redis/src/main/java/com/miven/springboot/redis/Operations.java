package com.miven.springboot.redis;

import com.miven.entity.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *  操作
 * @author mingzhi.xie
 * @date 2019/9/2
 * @since 1.0
 */
@Slf4j
@Component
public class Operations {

    @Resource
    private RedisTemplate<String, Email> redisTemplate;

    void setEmail(Email email) {
        log.info("Set cache to redis: " + email.toString());
        redisTemplate.opsForValue().set("Email", email);
    }

    Email getEmail(Email email) {
        Email emailCache = redisTemplate.opsForValue().get("Email");
        log.info("From redis get cache: " + emailCache);
        if (emailCache != null) {
            return  emailCache;
        }

        try {
            Thread.sleep(5000L);
            log.warn("Not found Email in Redis, so again set email cache to redis! time 5000 millisecond.");
            setEmail(email);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return email;
    }

    void removeEmail(Email email) {
        log.info("From redis remove cache: " + email);
        redisTemplate.delete("Email");
    }
}
