FROM centos7.5:openJDK
VOLUME /tmp
ADD target/springboot-start.jar springboot-start.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "springboot-start.jar" ]