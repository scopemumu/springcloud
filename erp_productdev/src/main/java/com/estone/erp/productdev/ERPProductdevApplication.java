package com.estone.erp.productdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

/***
 * 产品开发应用
 * 
 * @author Kevin
 *
 */
@SpringBootApplication(scanBasePackages = { "com.estone.erp" })
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients(basePackages = { "com.estone.erp" })
@ImportResource(value = { "classpath:application-mybatis.xml" })
@EntityScan(basePackages = { "com.estone.erp.*.mongo.model.bo" })
public class ERPProductdevApplication {
    public static void main(String[] args) {
        SpringApplication.run(ERPProductdevApplication.class, args);
    }
}
