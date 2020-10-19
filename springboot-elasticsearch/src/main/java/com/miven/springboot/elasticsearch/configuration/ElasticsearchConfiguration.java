package com.miven.springboot.elasticsearch.configuration;

import com.google.gson.Gson;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.AggregatorFactories;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import static org.elasticsearch.index.query.Operator.AND;
import static org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval.minutes;

/**
 * @author mingzhi.xie
 * @since 2020/10/15 1.0
 */
@Configuration
public class ElasticsearchConfiguration extends AbstractElasticsearchConfiguration {

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("127.0.0.1:9200")
                .withBasicAuth("dev", "***")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            RestHighLevelClient restHighLevelClient = elasticsearchClient();

            RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder("@timestamp")
                    .gte("1602751640636")
                    .lte("1602924440637")
                    .format("epoch_millis");

            QueryStringQueryBuilder stringQueryBuilder = new QueryStringQueryBuilder("app_id:***");

            BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
                    .filter(rangeQueryBuilder)
                    .filter(stringQueryBuilder);

            AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders
                    .avg("load_time")
                    .field("load_time");
            AggregationBuilder aggregationBuilder = AggregationBuilders
                    .dateHistogram("time")
                    .field("@timestamp")
                    .fixedInterval(minutes(2))
                    .extendedBounds(new ExtendedBounds(1602751640636L, 1602924440637L))
                    .format("epoch_millis")
                    .subAggregation(avgAggregationBuilder);

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(boolQueryBuilder);
            searchSourceBuilder.aggregation(aggregationBuilder);
            searchSourceBuilder.from(0);
            searchSourceBuilder.size(2);

            SearchRequest searchRequest = new SearchRequest("track_performance-*");
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = searchResponse.getAggregations();
            Aggregation time = aggregations.get("time");
            System.out.println(new Gson().toJson(time));
        };
    }
}
