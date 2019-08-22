package com.miven.springboot.demo;

import org.springframework.stereotype.Service;

/**
 *
 * @author mingzhi.xie
 * @date 2018/9/27
 */
@Service
public class HomeService {

    String getHome() {
        return "Hello World!";
    }
}
