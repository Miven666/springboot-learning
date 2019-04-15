package com.miven.springboot.mybatis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 水果
 * @author mingzhi.xie
 * @date 2019/3/20
 */

@Data
public class Fruit implements Serializable {

    private static final long serialVersionUID = 1047204607917402451L;

    private int id;

    private String name;

    private int number;


}
