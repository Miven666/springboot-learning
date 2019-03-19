package com.miven.springboot.start.component;

import org.springframework.stereotype.Service;

/**
 *
 * @author mingzhi.xie
 * @date 2018/9/27
 */
@Service
public class HomeService {

    public String getHome() {
        return "Hello World!";
    }
}
