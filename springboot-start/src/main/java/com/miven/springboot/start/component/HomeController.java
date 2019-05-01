package com.miven.springboot.start.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mingzhi.xie
 * @date 2018/9/27
 */
@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;

    @RequestMapping("/")
    String home() {
        return homeService.getHome();
    }
}