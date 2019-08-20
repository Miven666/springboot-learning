package com.miven.springboot.start.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author mingzhi.xie
 * @date 2019/8/15
 * @since 1.0
 */
@Data
public class Home {
    @NotBlank
    private String name;

    private Door door;
}
