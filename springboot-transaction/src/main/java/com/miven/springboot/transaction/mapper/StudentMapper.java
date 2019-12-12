package com.miven.springboot.transaction.mapper;

import com.miven.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * 学生
 * @author mingzhi.xie
 * @date 2019/12/12
 * @since 1.0
 */
@Repository
public interface StudentMapper {
    /**
     * 根据Id 更新学生信息
     * @param student 学生
     * @return 更新数
     */
    int updateById(Student student);
}