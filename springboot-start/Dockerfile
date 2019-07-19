FROM centos:centos7.5.1804
MAINTAINER mingzhi.xie "xie6032mail@163.com"
RUN yum -y install java-1.8.0-openjdk
ENV LC_ALL "en_US.UTF-8"
VOLUME /tmp
ADD target/springboot-start.jar springboot-start.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "springboot-start.jar" ]