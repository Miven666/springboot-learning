package com.miven.springboot.transaction;

import com.miven.entity.Student;
import com.miven.springboot.transaction.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 老师
 * @author mingzhi.xie
 * @date 2019/12/12
 * @since 1.0
 */
@Service
public class StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void update(int teacherId, int age, String name) {
        Student student = Student.builder()
                .age(age)
                .name(name)
                .teacherId(teacherId)
                .build();

        studentMapper.updateById(student);
    }
}