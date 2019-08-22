# SpringBoot-demo

## [启动流程](http://oss-static.hz5800.com/201905/c0a4ba8a3ff853501192bbf493914eafa11f86ab155d997057f195389f9397f0.png)
1. 通过`SpringFactoriesLoader`加载`META-INF/spring.factories`文件，获取并创建运行监听器`SpringApplicationRunListener`对象
2. 由监听器`SpringApplicationRunListener`来发出`starting`消息
3. 创建参数，并配置`Environment`
4. 由监听器`SpringApplicationRunListener`来发出`environmentPrepared`消息
5. 创建`IOC`容器`ApplicationContext`
6. 设置容器的`Environment`，处理相关配置`postProcessApplicationContext`，初始化`IOC`容器
7. 由监听器`SpringApplicationRunListener`来发出`contextPrepared`消息
8. 将各种`beans`装载进`IOC`容器，继续由监听器`SpringApplicationRunListener`来发出`contextLoaded`消息
9. 刷新`IOC`容器，完成IoC容器可用的最后一步
10. 由监听器`SpringApplicationRunListener`来发出`started`消息
11. 运行`IOC`容器
12. 由监听器`SpringApplicationRunListener`来发出`running`消息

## 注解说明
- 开启组件扫描 `@ComponentScan`
- 开启自动配置 `@EnableAutoConfiguration`
>  这个注释告诉Spring Boot根据你添加的jar依赖关系“猜测”你想要如何配置Spring
   如我们在pom.xml中添加了spring-boot-starter-web依赖
   它就会假定我们正在开发Web应用程序并相应地设置Spring
   虽然springboot很会“猜测”，但是总有猜不中我们心思的时候，比如当我们的web项目需要支持跨域访问
   自然而然我们就需要做相应的配置
