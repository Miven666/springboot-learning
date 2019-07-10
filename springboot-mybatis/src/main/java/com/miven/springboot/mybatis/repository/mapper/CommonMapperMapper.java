package com.miven.springboot.mybatis.repository.mapper;

import com.miven.springboot.mybatis.entity.CommonMapper;
import com.miven.springboot.mybatis.repository.BasicMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author mingzhi.xie
 * @date 2019/7/8
 * @since 1.0
 */
@Repository
public interface CommonMapperMapper extends BasicMapper<CommonMapper> {
    /**
     * 根据name查询
     * @param name
     * @return CommonMapper
     */
    CommonMapper selectByName(@Param("name") String name);
}