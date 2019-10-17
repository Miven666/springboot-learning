package com.miven.springboot.validate;

import com.miven.entity.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * 服务层
 * @author mingzhi.xie
 * @date 2019/10/17
 * @since 1.0
 */
@Slf4j
@Service
@Validated
public class ValidationService {
    /**
     *  无效
     */
    public Fruit validatedPojo(@Validated Fruit fruit) {
        log.info(fruit.toString());
        return fruit;
    }

    public Fruit validPojo(@Valid Fruit fruit) {
        log.info(fruit.toString());
        return fruit;
    }
}
