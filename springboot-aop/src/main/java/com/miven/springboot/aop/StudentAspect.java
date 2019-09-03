package com.miven.springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 *  切面配置
 * @author mingzhi.xie
 * @date 2019/9/3
 * @since 1.0
 */
@Slf4j
@Aspect
@Configuration
public class StudentAspect {

    @Around(value = "execution(* com.miven.springboot.aop.StudentComponent.*(..))")
    public Object intercept(ProceedingJoinPoint point) throws Throwable {
        log.info("Invocation around method");
        return point.proceed();
    }
}
