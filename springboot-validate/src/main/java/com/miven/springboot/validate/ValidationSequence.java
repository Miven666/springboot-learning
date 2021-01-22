package com.miven.springboot.validate;

import com.miven.entity.Fruit;
import com.miven.entity.validation.groups.Delete;
import com.miven.entity.validation.groups.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.groups.Default;

/**
 * 组校验顺序
 * @author mingzhi.xie
 * @date 2019/10/31
 * @since 1.0
 */
@Slf4j
@Component
@Validated({Default.class, Query.class, Delete.class})
public class ValidationSequence {

    public Fruit validQueryDelete(@Valid Fruit fruit) {
        log.info(fruit.toString());
        return fruit;
    }
}
