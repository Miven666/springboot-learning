package com.miven.springboot.start.component;

import org.springframework.stereotype.Service;

/**
 * Created by mingzhi.xie on 2018/9/27.
 */
@Service
public class HomeService {

    public String getHome() {
        return "Hello World!";
    }
}
