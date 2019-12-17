package com.miven.springboot.dds.jpa.web;

import com.miven.springboot.dds.jpa.dynamic.model.User;
import com.miven.springboot.dds.jpa.dynamic.service.UserService;
import com.miven.springboot.dds.jpa.support.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 登录控制器
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
@Slf4j
@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @GetMapping("/login.html")
    public String login(){
        return "/login";
    }

    @PostMapping("/login")
    public String login(ModelMap model,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password) {
        log.info("tenant : {}", TenantContextHolder.getTenant());
        User user = userService.findByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            model.put("user",user);
            return "/index";
        }

        return "/login";
    }
}