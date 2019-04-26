# SpringBoot-learning
> 2004年3月24日 Spring Framework 1.0 Final Released

## spring-beans
### org.springframework.beans.factory.config
- BeanDefinition `1.0`
- BeanFactory `1.0`
- BeanDefinitionRegistry `1.0`
- BeanDefinitionReader `1.1`

## spring-core
### org.springframework.core.type
- ResourceLoader `1.0`
- ClassMetadata `2.5`
- AnnotationMetadata `2.5`
- MethodMetadata `3.0`
- AnnotatedTypeMetadata `4.0`









##  RestTemplate
### 配置
- 在 RestTemplateAutoConfiguration 自动配置类注册了 RestTemplateBuilder@Bean
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-autoconfigure</artifactId>
</dependency>
```
-  有了RestTemplateBuilder 就可以 build() 出 RestTemplate 了
-  也可以编写配置类 RestTemplateConfig 提前注册RestTemplate@Bean，需要的时候注入就好
### 关于Response Headers 中 Content-Type
> 错误提示  Could not extract response: no suitable HttpMessageConverter found for response type [class ] and content type [text/javascript;charset=UTF-8]
分析结果： 这是由于 HTTP请求中Response Headers 的 Content-Type 某种类型 Spring 中的HttpMessageConverter 没支持

```java
@Configuration
public class RestTemplateConfig {
    
	private final static Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);
    
    @Resource
    private RestTemplateBuilder builder;
	
    /**
    *  为Content-Type添加对 'text/javascript' 类型的支持
	*/
    @Bean
    public RestTemplate restTemplate() {
        // 先获取到converter列表
        List<HttpMessageConverter<?>> converters = builder.build().getMessageConverters();
        for(HttpMessageConverter<?> converter : converters){
            // 根据提供方的数据格式选择对应的转化器（一般api接口都是json）
            if(converter instanceof MappingJackson2HttpMessageConverter){
                try{
                    // 先将原先支持的MediaType列表拷出
                    List<MediaType> mediaTypeList = new ArrayList<>(converter.getSupportedMediaTypes());
                    // 加入对text/javascript的支持
                    mediaTypeList.add(MediaType.parseMediaType("text/javascript"));
                    ((MappingJackson2HttpMessageConverter) converter).setSupportedMediaTypes(mediaTypeList);
                }catch(Exception e){
                    logger.error("Add Content-Type:'text/javascript' is error", e);
                }
            }
        }
        return builder.build();
    }

}
```
