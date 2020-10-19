package com.miven.springboot.elasticsearch.entity.grafana.plugin.json;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mingzhi.xie
 * @since 2020/10/16 1.0
 */
@Getter
public enum Metric {
    /**
     * 加载时间
     */
    LOAD_TIME("load_time", "加载时间"),
    /**
     * 白屏时间
     */
    WHITE_SCREEN("white_screen", "白屏时间")
    ;

    private final String fieldName;

    private final String text;

    Metric(String fieldName, String text) {
        this.fieldName = fieldName;
        this.text = text;
    }

    public static Metric parseByFieldName(String fieldName) {
        if (StringUtils.isBlank(fieldName)) {
            return null;
        }
        for (Metric value : values()) {
            if (value.fieldName.endsWith(fieldName)) {
                return value;
            }
        }
        return null;
    }

    public static List<SearchDTO> metrics() {
        ArrayList<SearchDTO> metrics = new ArrayList<>();
        for (Metric value : Metric.values()) {
            SearchDTO search = new SearchDTO();
            search.setText(value.getText());
            search.setValue(value.getFieldName());
            metrics.add(search);
        }
        return metrics;
    }
}
