package com.miven.springboot.mybatis.mapper;

import com.miven.springboot.mybatis.entity.Fruit;
import org.apache.ibatis.annotations.Mapper;

/**
 * 水果
 * @author mingzhi.xie
 * @date 2019/3/20
 */
@Mapper
public interface FruitMapper {

    /**
     * 新增
     * @param fruit 水果
     * @return      行数
     */
    int insert(Fruit fruit);
}
