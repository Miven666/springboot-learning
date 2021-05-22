package com.miven.springboot.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 并发请求示例
 *
 * @author mingzhi.xie
 * @since 2021/5/22 1.0.0
 */
@Slf4j
@RestController
public class ConcurrentController {

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private RestTemplateBuilder restTemplateBuilder;

    @GetMapping("/demo/test")
    public void demo() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = threadPoolTaskExecutor.getThreadPoolExecutor();
        threadPoolExecutor.setCorePoolSize(20);
        RestTemplate restTemplate = restTemplateBuilder.build();
        long startTime = System.currentTimeMillis();
        ArrayList<Callable<String>> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(() -> {
                String result = restTemplate.getForObject("https://localhost:8080/", String.class);
                log.info(result);
                return result;
            });
        }
        while (System.currentTimeMillis() - startTime <= 60000) {
            threadPoolExecutor.invokeAll(list);
        }
    }
}
