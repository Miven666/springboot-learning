package com.miven.springboot.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 *  接收者
 * @author mingzhi.xie
 * @date 2019/8/23
 * @since 1.0
 */
@Slf4j
@Component
public class Receiver {

    private final CountDownLatch latch;

    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        log.info("Received <" + message + ">");
        this.latch.countDown();
    }
}
