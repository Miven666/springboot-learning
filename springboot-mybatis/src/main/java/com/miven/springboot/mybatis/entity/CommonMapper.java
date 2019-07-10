package com.miven.springboot.mybatis.entity;

import javax.persistence.*;
import lombok.Data;

/**
 * @author mingzhi.xie
 * @date 2019/7/8
 * @since 1.0
 */
@Data
@Table(name = "common_mapper")
public class CommonMapper {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    private String name;
}