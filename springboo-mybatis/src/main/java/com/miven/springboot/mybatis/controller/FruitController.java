package com.miven.springboot.mybatis.controller;

import com.miven.springboot.mybatis.entity.Fruit;
import com.miven.springboot.mybatis.mapper.FruitMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/insert")
    public String insert() {
        Fruit fruit = new Fruit();
        fruit.setName("西瓜");
        fruit.setNumber(10);
        fruitMapper.insert(fruit);
        return JSONParser.quote("OK");
    }
}
