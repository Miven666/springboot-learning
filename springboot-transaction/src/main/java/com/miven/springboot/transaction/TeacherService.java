package com.miven.springboot.transaction;

import com.miven.entity.Teacher;
import com.miven.springboot.transaction.mapper.TeacherMapper;
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
public class TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private StudentService studentService;

    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public void update(int id, int age, String name) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setAge(age);
        teacher.setName(name);

        teacherMapper.updateById(teacher);
        studentService.update(1, 10, "小屁孩");
    }
}