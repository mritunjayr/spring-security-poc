package com.stackroute.springsecurity;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    public ServletWebServerFactory servletConatainer(){
        //Enable ssl traffic
        TomcatServletWebServerFactory tomcat= new TomcatServletWebServerFactory(){
            @Override
            public void postProcessContext(Context context){
                SecurityConstraint securityConstraint=new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection=new SecurityCollection();
                collection.addPattern("/**");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);

            }
        };
        //add HTTP to HTTPS redirect
        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
        return tomcat;
    }

    private Connector httpToHttpsRedirectConnector(){
        Connector connector=new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8082);
        connector.setSecure(true);
        connector.setRedirectPort(8443);
        return connector;
    }
}

/*
this is command to create ssl self assigned certificate:-

keytool -genkeypair -alias bootsecurity -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore bootsecurity.p12 -validity 3650
*/