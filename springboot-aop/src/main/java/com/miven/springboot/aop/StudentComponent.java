package com.miven.springboot.aop;

import com.miven.entity.Student;
import org.springframework.stereotype.Component;

/**
 *  学生组件
 * @author mingzhi.xie
 * @date 2019/9/3
 * @since 1.0
 */
@Component
public class StudentComponent {

    public Student getStudent() {
        return Student.builder().id(100).name("Miven").build();
    }
}
