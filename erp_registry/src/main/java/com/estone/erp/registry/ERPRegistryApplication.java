package com.estone.erp.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/***
 * 注册服务应用
 * 
 * @author Kevin
 *
 */
@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@EnableEurekaServer
public class ERPRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ERPRegistryApplication.class, args);
    }
}
