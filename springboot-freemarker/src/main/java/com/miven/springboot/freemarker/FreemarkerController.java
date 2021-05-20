package com.miven.springboot.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器
 *
 * @author mingzhi.xie
 * @since 2021/1/21 1.0.0
 */
@Controller
public class FreemarkerController {

    @RequestMapping("/demo/index")
    public String demoIndex(Model model) {
        model.addAttribute("name","hello world");
        return "index";
    }

    @ResponseBody
    @RequestMapping("/index/{name}")
    public String indexName(@PathVariable String name, Model model) {
        model.addAttribute("name","hello world");
        return "index-name";
    }
}
