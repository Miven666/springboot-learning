package com.miven.springboot.elasticsearch.entity.grafana.plugin.json;

import lombok.Data;

import java.util.List;

/**
 * @author mingzhi.xie
 * @since 2020/10/15 1.0
 */
@Data
public class QueryRequestVO {
        private String app;
        private String requestId;
        private String timezone;
        private Long panelId;
        private Long dashboardId;
        private RangeDTO range;
        private String timeInfo;
        private String interval;
        private Integer intervalMs;
        private List<TargetDTO<?>> targets;
        private Integer maxDataPoints;
        // private ScopedVars scopedVars;
        private Long startTime;
        // private RangeRaw rangeRaw;
        private List<String> adhocFilters;
}
