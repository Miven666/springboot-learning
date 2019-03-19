# SpringBoot 整合 Mybatis

## 为整合前
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