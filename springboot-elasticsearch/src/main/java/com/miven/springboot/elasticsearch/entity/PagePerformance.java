package com.miven.springboot.elasticsearch.entity;

import com.miven.springboot.elasticsearch.entity.grafana.plugin.json.Metric;
import lombok.Data;

/**
 * @author mingzhi.xie
 * @since 2020/10/15 1.0
 */
@Data
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
public class PagePerformance {

    private String load_time;
    private String app_id;

    private String white_screen;
    private String server_time;

    public String metric(Metric metric) {
        switch (metric) {
            case LOAD_TIME:
                return getLoad_time();
            case WHITE_SCREEN:
                return getWhite_screen();
            default:
                return "";
        }
    }
}
