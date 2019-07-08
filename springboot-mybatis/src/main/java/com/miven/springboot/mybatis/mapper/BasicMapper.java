package com.miven.springboot.mybatis.mapper;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author mingzhi.xie
 * @date 2019/7/5
 * @since 1.0
 */
public interface BasicMapper<T> extends Mapper<T>, ConditionMapper<T>, IdsMapper<T>, MySqlMapper<T> {

}
