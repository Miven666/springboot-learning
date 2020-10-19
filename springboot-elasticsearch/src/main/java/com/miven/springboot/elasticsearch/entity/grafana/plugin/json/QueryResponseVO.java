package com.miven.springboot.elasticsearch.entity.grafana.plugin.json;

import lombok.Data;

import java.util.List;

/**
 * @author mingzhi.xie
 * @since 2020/10/14 1.0
 */
@Data
public class QueryResponseVO {

    private String target;

    private List<?> datapoints;
}
