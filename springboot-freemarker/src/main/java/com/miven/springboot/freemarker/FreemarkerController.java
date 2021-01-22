package com.miven.springboot.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制器
 *
 * @author mingzhi.xie
 * @since 2021/1/21 1.0.0
 */
@Controller
public class FreemarkerController {

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("name","hello pillar");
        return "index";
    }
}
