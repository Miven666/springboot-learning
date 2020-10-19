package com.miven.springboot.elasticsearch.controller;

import com.miven.springboot.elasticsearch.entity.grafana.plugin.json.QueryResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author mingzhi.xie
 * @since 2020/10/15 1.0
 */
@Slf4j
@RestController
@RequestMapping
public class IndexController {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    @Resource
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @GetMapping("/search")
    public List<QueryResponseVO> get(@RequestParam String index) {
        return null;
    }
}
