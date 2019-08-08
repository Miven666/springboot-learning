package com.miven.springboot.start.config;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.BeansException;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Http/1.1自动配置
 * @author mingzhi.xie
 * @date 2019/7/31
 * @since 1.0
 */
public class Http11NioProtocolAutoConfiguration implements ApplicationContextAware {

    private Http11NioProtocol protocol;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (!(applicationContext instanceof ServletWebServerApplicationContext)) {
            return;
        }
        ServletWebServerApplicationContext context = (ServletWebServerApplicationContext) applicationContext;
        WebServer webServer = context.getWebServer();
        if (!(webServer instanceof TomcatWebServer)) {
            return;
        }
        TomcatWebServer tomcatWebServer = (TomcatWebServer) webServer;
        Tomcat tomcat = tomcatWebServer.getTomcat();
        Connector connector = tomcat.getConnector();
        ProtocolHandler protocolHandler = connector.getProtocolHandler();
        if (!(protocolHandler instanceof Http11NioProtocol)) {
            return;
        }
        setProtocol((Http11NioProtocol) protocolHandler);
        getProtocol().setRelaxedPathChars("|{}[],/");
        getProtocol().setRelaxedQueryChars("|{}[],/");
    }

    private Http11NioProtocol getProtocol() {
        return protocol;
    }

    private void setProtocol(Http11NioProtocol protocol) {
        this.protocol = protocol;
    }
}
