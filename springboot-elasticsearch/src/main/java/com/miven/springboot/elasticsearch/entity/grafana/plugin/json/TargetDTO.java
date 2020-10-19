package com.miven.springboot.elasticsearch.entity.grafana.plugin.json;

import lombok.Data;

import java.util.Map;

/**
 * @author mingzhi.xie
 * @since 2020/10/16 1.0
 */
@Data
public class TargetDTO<T> {
    private Map<String, Object> data;
    private String datasource;
    private String refId;
    private T target;
    private String type;
}
