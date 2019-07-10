package com.miven.springboot.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.miven.springboot.mybatis.entity.Fruit;
import com.miven.springboot.mybatis.repository.mapper.CommonMapperMapper;
import com.miven.springboot.mybatis.repository.mapper.FruitMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 水果
 * @author mingzhi.xie
 * @date 2019/3/20
 */
@RestController
@RequestMapping("/fruit")
public class FruitController {

    @Resource
    private FruitMapper fruitMapper;

    @Resource
    private CommonMapperMapper cmm;

    @GetMapping("/insert")
    public String insert() {
        Fruit fruit = new Fruit();
        fruit.setName("西瓜");
        fruit.setNumber(10);
        fruitMapper.insert(fruit);
        return JSONParser.quote("OK");
    }

    @GetMapping("/select")
    public String select(@RequestParam("name") String name) {
        Fruit fruit = new Fruit();
        fruit.setName(name);
        return JSON.toJSONString(fruitMapper.select(fruit, "name"));
    }

    @GetMapping("/mapper")
    public String mapper(@RequestParam("name") String name) {
        return JSON.toJSONString(cmm.selectByName(name));
    }
}
