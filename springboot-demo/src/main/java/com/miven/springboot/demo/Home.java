package com.miven.springboot.demo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author mingzhi.xie
 * @date 2019/8/15
 * @since 1.0
 */
@Data
class Home {
    @NotBlank
    private String name;

    private Door door;
}
