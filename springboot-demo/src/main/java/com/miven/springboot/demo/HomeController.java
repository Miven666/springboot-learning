package com.miven.springboot.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 控制层
 * @author mingzhi.xie
 * @date 2018/9/27
 */
@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    private HomeService homeService;

    /**
     *  未指定请求方式，get、post都可以
     * @return String 默认"Content-Type: text/plain;charset=UTF-8"
     */
    @RequestMapping("/door")
    public String door() {
        return homeService.getHome();
    }

    /**
     * get请求一般状况下无请求体，但是也是可以有请求体的
     * @param home 请求体
     */
    @GetMapping(value = "/desk")
    public String desk(@RequestBody Home home) {
        log.debug(home.toString());
        return homeService.getHome();
    }

    @PostMapping(value = "/window", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String window(@RequestBody Home home) {
        log.debug(home.toString());
        return homeService.getHome();
    }
}
