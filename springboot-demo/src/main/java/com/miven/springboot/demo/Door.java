package com.miven.springboot.demo;

import com.miven.spring.context.aware.CustomImportAware;
import org.springframework.context.annotation.Import;

/**
 *  门
 * @author mingzhi.xie
 * @date 2019/8/15
 * @since 1.0
 */
@SuppressWarnings(value = "")
@Import(CustomImportAware.class)
public enum Door {
    // 房门
    DOOR,

    // 大门
    Gate;

    Door() {
    }
}
