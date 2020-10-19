package com.miven.springboot.elasticsearch.entity.grafana.plugin.json;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mingzhi.xie
 * @since 2020/10/16 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RangeDTO extends RangeTimeDTO {

    private RangeTimeDTO raw;

}
