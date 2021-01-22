package com.miven.springboot.scheduled;

import org.springframework.scheduling.annotation.Scheduled;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mingzhi.xie
 * @since 2020/12/17 1.0.0
 *
 * @see org.springframework.scheduling.annotation.Scheduled
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ScheduledTask {

    String[] profiles();

    Scheduled scheduled();
}
