package com.estone.erp.usermgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

/***
 * 用户管理应用
 * 
 * @author Kevin
 *
 */
@SpringBootApplication(scanBasePackages = { "com.estone.erp" })
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients(basePackages = { "com.estone.erp" })
@ImportResource(value = { "classpath:application-mybatis.xml" })
public class ERPUsermgtApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ERPUsermgtApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ERPUsermgtApplication.class, args);
    }
}
