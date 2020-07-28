package com.miven.springboot.validate;

import com.miven.entity.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 控制层
 * @author mingzhi.xie
 * @date 2019/10/17
 * @since 1.0
 */
@Slf4j
@RestController
public class ValidationController {

    @Resource
    private ValidationService validationService;

    @Resource
    private ValidationComponent validationComponent;

    @Resource
    private ValidationQueryGroup validationQueryGroup;

    @Resource
    private ValidationDeleteGroup validationDeleteGroup;

    @Resource
    private ValidationSequence validationSequence;

    @GetMapping("/validated/fruit")
    public Fruit validatedPojo(@Validated Fruit fruit) {
        log.info(fruit.toString());
        return fruit;
    }

    @PostMapping("/validated/fruit")
    public Fruit validatedPojoPost(@Validated @RequestBody Fruit fruit) {
        log.info(fruit.toString());
        return fruit;
    }

    @GetMapping("/valid/fruit")
    public Fruit validPojo(@Valid Fruit fruit) {
        log.info(fruit.toString());
        return fruit;
    }

    @GetMapping("/validated/fruit/service")
    public Fruit validatedServicePojo(@Validated Fruit fruit) {
        log.info(fruit.toString());
        fruit.setName(null);
        return validationService.validatedPojo(fruit);
    }

    @GetMapping("/valid/fruit/service")
    public Fruit validServicePojo(@Valid Fruit fruit) {
        log.info(fruit.toString());
        fruit.setName(null);
        return validationService.validPojo(fruit);
    }

    @GetMapping("/validated/fruit/component")
    public Fruit validatedComponentPojo(@Validated Fruit fruit) {
        log.info(fruit.toString());
        fruit.setName(null);
        fruit.setNumber(0);
        return validationComponent.validatedPojo(fruit);
    }

    @GetMapping("/valid/fruit/component")
    public Fruit validComponentPojo(@Valid Fruit fruit) {
        log.info(fruit.toString());
        fruit.setName(null);
        fruit.setNumber(0);
        fruit.setConsumer(null);
        return validationComponent.validPojo(fruit);
    }


    @GetMapping("/validated/fruit/group/query")
    public Fruit validatedQueryGroupPojo(@Validated Fruit fruit) {
        log.info(fruit.toString());
        fruit.setName(null);
        return validationQueryGroup.validatedPojo(fruit);
    }


    @GetMapping("/valid/fruit/group/query")
    public Fruit validQueryGroupPojo(@Valid Fruit fruit) {
        log.info(fruit.toString());
        fruit.setName(null);
        return validationQueryGroup.validPojo(fruit);
    }

    @GetMapping("/validated/fruit/group/delete")
    public Fruit validatedDeleteGroupPojo(@Validated Fruit fruit) {
        log.info(fruit.toString());
        fruit.setNumber(0);
        return validationDeleteGroup.validatedPojo(fruit);
    }

    @GetMapping("/valid/fruit/group/delete")
    public Fruit validDeleteGroupPojo(@Valid Fruit fruit) {
        log.info(fruit.toString());
        fruit.setNumber(0);
        return validationDeleteGroup.validPojo(fruit);
    }

    @GetMapping("/valid/fruit/group/sequence")
    public Fruit validSequence(@Valid Fruit fruit, BindingResult bindingResult) {
        log.info(fruit.toString());
        fruit.setName(null);
        fruit.setConsumer(null);
        fruit.setNumber(0);
        log.info(bindingResult.toString());
        return validationSequence.validQueryDelete(fruit);
    }
}
