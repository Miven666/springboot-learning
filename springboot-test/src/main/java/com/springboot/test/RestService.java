package com.springboot.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author mingzhi.xie
 * @since 2021/1/4 1.0.0
 */
@Slf4j
@Service
public class RestService implements ApplicationRunner {

    @Resource
    private RestTemplateBuilder restTemplateBuilder;

    public void urlBan() {
        RestTemplate restTemplate = restTemplateBuilder
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create()
                        .setRedirectStrategy(new UrlBannedRedirectStrategy())
                        .build())).build();
        try {
            restTemplate.exchange("https://mmbizurl.cn/s/4zSTSgx3B", HttpMethod.GET, null, byte[].class);
        } catch (Exception e) {
            log.warn("请求异常: {}", e.getMessage(), e);
        }
    }

    @Override
    public void run(ApplicationArguments args) {
        urlBan();
    }

    public static class UrlBannedRedirectStrategy implements RedirectStrategy {

        private final DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        @Override
        public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
            return this.redirectStrategy.isRedirected(request, response, context);
        }

        @Override
        public HttpUriRequest getRedirect(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
            HttpUriRequest httpUriRequest = this.redirectStrategy.getRedirect(request, response, context);
            String host = httpUriRequest.getURI().getHost();
            log.info(host);
            return httpUriRequest;
        }
    }
}
