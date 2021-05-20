package com.miven.springboot.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 路径变量研究示例
 *
 * @author mingzhi.xie
 * @since 2021/4/22 1.0.0
 */
@Slf4j
@RestController
public class PathVariableController {

    @GetMapping(path = "/path/{test}")
    public void path(@PathVariable String test) {
      log.info(test);
    }
}
