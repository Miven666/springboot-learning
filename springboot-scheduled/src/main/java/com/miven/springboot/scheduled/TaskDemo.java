package com.miven.springboot.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务示例
 *
 * @author mingzhi.xie
 * @since 2020/12/17 1.0.0
 */
@Slf4j
@Component
public class TaskDemo {

    @Scheduled(cron = "0/10 * * * * ?")
    public void demo1() {
        log.info("=======11111==========");
    }

    @ScheduledTask(profiles = "dev", scheduled = @Scheduled(cron = "0/10 * * * * ?"))
    public void demo2() {
        log.info("======22222===========");
    }
}
