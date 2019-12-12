package com.miven.springboot.transaction.mapper;

import com.miven.entity.Teacher;
import org.springframework.stereotype.Repository;

/**
 * 老师
 * @author mingzhi.xie
 * @date 2019/12/12
 * @since 1.0
 */
@Repository
public interface TeacherMapper {
    /**
     * 根据Id 更新老师信息
     * @param teacher 老师
     * @return 更新数
     */
    int updateById(Teacher teacher);
}