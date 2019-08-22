FROM centos7.5:openJDK
VOLUME /tmp
ADD target/springboot-demo.jar springboot-demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "springboot-demo.jar" ]