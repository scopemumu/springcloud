package com.estone.erp.productdev.message.mongo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.estone.erp.common.mongo.Mongos;

/***
 * message mongo配置文件，为指定的Repositoy注入mongoTemplate
 * 
 * @author Kevin
 *
 */
@Configuration
@EnableMongoRepositories(basePackages = {
        "com.estone.erp.productdev.message.mongo.repo" }, mongoTemplateRef = Mongos.MESSAGE_TEMPLATE)
public class MessageMongoConfig {
}
