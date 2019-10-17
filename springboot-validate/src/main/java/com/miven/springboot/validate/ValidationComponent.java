package com.miven.springboot.validate;

import com.miven.entity.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * 组件
 * @author mingzhi.xie
 * @date 2019/10/17
 * @since 1.0
 */
@Slf4j
@Validated
@Component
public class ValidationComponent {

    public Fruit validatedPojo(@Validated Fruit fruit) {
        log.info(fruit.toString());
        return fruit;
    }

    public Fruit validPojo(@Valid Fruit fruit) {
        log.info(fruit.toString());
        return fruit;
    }
}
