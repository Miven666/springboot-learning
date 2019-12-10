package com.miven.springboot.jta;

import com.miven.springboot.jta.mapper.db1.Db1Test1Mapper;
import com.miven.springboot.jta.mapper.db2.Db2Test1Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * 启动运行类
 * @author mingzhi.xie
 * @date 2019/12/10
 * @since 1.0
 */
@Slf4j
@Component
public class JtaRunner implements ApplicationRunner {
    @Resource
    private Db1Test1Mapper db1Test1Mapper;
    @Resource
    private Db2Test1Mapper db2Test1Mapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void run(ApplicationArguments args) {
        // 张三 -> 王五
        db1Test1Mapper.updateName(1, "王五");
        log.info("by zero");
        int i = 1/0;
        // 李四 -> 赵六
        db2Test1Mapper.updateName(1, "赵六");
    }
}