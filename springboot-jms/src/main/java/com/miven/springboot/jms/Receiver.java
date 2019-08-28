package com.miven.springboot.jms;

import com.miven.entity.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *  接受者
 * @author mingzhi.xie
 * @date 2019/8/27
 * @since 1.0
 */
@Slf4j
@Component
public class Receiver {

    @JmsListener(destination = "mailbox", containerFactory = "customJmsListenerContainerFactory")
    public void receiverMessage(Email email) {
        log.info("Received <" + email + ">");
    }
}
