package com.miven.springboot.mongodb.controller;

import com.alibaba.fastjson.JSON;
import com.miven.entity.Student;
import com.miven.springboot.mongodb.repository.MongoDao;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private MongoDao mongoDao;

    @PostMapping
    public String insert() {
        Student student = Student.builder().id(17).name("解明智").age(26).build();
        mongoDao.save(student);
        return "OK";
    }

    @GetMapping
    public String select() {
        Student student = mongoDao.query("name", "解明智");
        return JSON.toJSONString(student);
    }

    @PutMapping
    public String update() {
        mongoDao.updateById(18, "name", "miven");
        return "OK";
    }
}
