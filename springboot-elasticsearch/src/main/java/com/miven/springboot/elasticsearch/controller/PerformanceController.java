package com.miven.springboot.elasticsearch.controller;

import com.google.gson.Gson;
import com.miven.springboot.elasticsearch.entity.PagePerformance;
import com.miven.springboot.elasticsearch.entity.grafana.plugin.json.AnnotationsRequestVO;
import com.miven.springboot.elasticsearch.entity.grafana.plugin.json.Metric;
import com.miven.springboot.elasticsearch.entity.grafana.plugin.json.QueryRequestVO;
import com.miven.springboot.elasticsearch.entity.grafana.plugin.json.QueryResponseVO;
import com.miven.springboot.elasticsearch.entity.grafana.plugin.json.RangeDTO;
import com.miven.springboot.elasticsearch.entity.grafana.plugin.json.SearchDTO;
import com.miven.springboot.elasticsearch.entity.grafana.plugin.json.SearchRequestVO;
import com.miven.springboot.elasticsearch.entity.grafana.plugin.json.TargetDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.SimpleField;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 接入Grafana示例
 *
 * @author mingzhi.xie
 * @since 2020/10/14 1.0
 */
@Slf4j
@RestController
@RequestMapping("/performance")
public class PerformanceController {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    @Resource
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @GetMapping("/")
    public void healthCheck() {}

    @PostMapping("/search")
    public List<SearchDTO> search(@RequestBody SearchRequestVO requestVO) {
        if (StringUtils.isBlank(requestVO.getTarget())) {
            return  Metric.metrics();
        }
        return null;
    }

    @PostMapping("/query")
    public List<QueryResponseVO> query(@RequestBody QueryRequestVO requestVO) {
        IndexCoordinates index = IndexCoordinates.of("track_performance-*");
        PageRequest page = PageRequest.of(0, requestVO.getMaxDataPoints());

        RangeDTO range = requestVO.getRange();
        ZonedDateTime form = OffsetDateTime.parse(range.getFrom()).atZoneSameInstant(ZoneId.systemDefault());
        ZonedDateTime to = OffsetDateTime.parse(range.getTo()).atZoneSameInstant(ZoneId.systemDefault());
        SimpleField serverTimeField = new SimpleField("server_time");
        serverTimeField.setFieldType(FieldType.Date);
        Criteria rangeTimeCriteria = Criteria.where(serverTimeField).between(form, to);

        CriteriaQuery query = new CriteriaQuery(rangeTimeCriteria, page);

        ArrayList<QueryResponseVO> result = new ArrayList<>();
        List<TargetDTO<?>> targets = requestVO.getTargets();
        for (TargetDTO<?> targetDTO : targets) {
            String target = (String) targetDTO.getTarget();

            Metric metric = Metric.parseByFieldName(target);
            if (Objects.isNull(metric)) {
                continue;
            }

            if (Objects.nonNull(targetDTO.getData())) {
                Object appId = targetDTO.getData().get("appId");
                query.addCriteria(Criteria.where("app_id").in(appId));
            }

            List<SearchHit<PagePerformance>> hits = elasticsearchTemplate
                .search(query, PagePerformance.class, index).getSearchHits();

            List<?> dataPoints = hits.stream()
                    .sorted(Comparator.comparing(o -> LocalDateTime.parse(o.getContent().getServer_time(), formatter)))
                    .map((Function<SearchHit<PagePerformance>, Object>) pagePerformance -> {
                        PagePerformance content = pagePerformance.getContent();
                        long serverTime;
                        try {
                            serverTime = LocalDateTime.parse(content.getServer_time(), formatter)
                                    .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                        } catch (Exception e) {
                            log.warn(e.getMessage(), e);
                            return null;
                        }

                        double metricValue;
                        try {
                            metricValue = Double.parseDouble(content.metric(metric));
                        } catch (Exception e){
                            log.warn(e.getMessage(), e);
                            metricValue = 0.0;
                        }

                        ArrayList<Object> point = new ArrayList<>();
                        point.add(0,metricValue);
                        point.add(1, serverTime);
                        return point;
                    }).collect(Collectors.toList());

            QueryResponseVO responseVO = new QueryResponseVO();
            responseVO.setTarget(target);
            responseVO.setDatapoints(dataPoints);
            result.add(responseVO);
        }
        return result;
    }

    @PostMapping("/annotations")
    public List<?> annotations(@RequestBody AnnotationsRequestVO requestVO) {
        log.info(new Gson().toJson(requestVO));
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(new Gson().fromJson("{\"text\":\"环比上周有明显下滑\",\"title\":\"智能分析\",\"isRegion\":true" +
                ",\"time\":1602733833000,\"timeEnd\":1602737433000,\"tags\":[\"load_time\"]}", Map.class));
        return objects;
    }

}
