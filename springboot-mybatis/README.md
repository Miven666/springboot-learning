# SpringBoot 整合 Mybatis

## 未整合前
### SqlSessionFactoryBuilder
- 在不同条件下，使用建造者模式来统一创建SqlSessionFactory

### SqlSessionFactory
- 应用运行期间最好就一个SqlSessionFactory（单例模式或者静态单例模式）
- 为不同的线程创建各自的SqlSession

### SqlSession
- 线程不安全
- 确保完成响应后，关闭它

## 整合
### mybatis-spring
- SqlSessionFactoryBean实现了Spring的InitializingBean
- 初始化了SqlSessionFactoryBuilder，通过buildSqlSessionFactory()方法构造了SqlSessionFactory
- SqlSessionTemplate实现了SqlSession

### mybatis-spring-boot-autoconfigure
- MybatisAutoConfiguration实现了Spring的InitializingBean
- 在利用@Bean依赖注入SqlSessionFactory时，调用SqlSessionFactoryBean获取了SqlSessionFactory
- 利用@Bean依赖注入了SqlSessionTemplate

### mybatis-generator-maven-plugin
- mybatis相关代码生成插件
- 在pom.xml的plugins节点中添加
  ```xml
  <plugin>
      <groupId>org.mybatis.generator</groupId>
      <artifactId>mybatis-generator-maven-plugin</artifactId>
      <version>1.3.7</version>
      <configuration>
          <configurationFile>
             ${basedir}/src/main/resources/generator/generatorConfig.xml
      	</configurationFile>
          <overwrite>true</overwrite>
          <verbose>true</verbose>
      </configuration>
      <dependencies>
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>5.1.46</version>
          </dependency>
      </dependencies>
  </plugin>
  ```
- 配置[generatorConfig.xml](https://github.com/Miven666/SpringBoot-learing/blob/generator/springboo-mybatis/src/main/resources/generator/generatorConfig.xml)
