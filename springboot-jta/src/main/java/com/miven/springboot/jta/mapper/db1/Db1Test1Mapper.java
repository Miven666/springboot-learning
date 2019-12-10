package com.miven.springboot.jta.mapper.db1;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 测试1 Mapper
 * @author mingzhi.xie
 * @date 2019/12/10
 * @since 1.0
 */
@Repository
public interface Db1Test1Mapper {
    /**
     * 根据ID更新名称
     * @param id ID
     * @param name 名称
     */
    void updateName(@Param("id") int id, @Param("name") String name);
}