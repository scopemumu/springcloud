package com.estone.erp.productdev.wish.mongo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.estone.erp.common.mongo.AbstractMongoConfig;
import com.estone.erp.common.mongo.Mongos;

/***
 * wish mongo配置文件，为指定的Repositoy注入mongoTemplate
 * 
 * @author Kevin
 *
 */
@Configuration
@EnableMongoRepositories(basePackages = {
        "com.estone.erp.productdev.wish.mongo.repo" }, mongoTemplateRef = Mongos.WISH_TEMPLATE)
@ConfigurationProperties(prefix = "spring.data.mongodb.wish")
public class WishMongoConfig extends AbstractMongoConfig {
    @Autowired
    private ApplicationContext appContext;

    @Override
    @Qualifier
    @Bean(name = Mongos.WISH_TEMPLATE)
    public MongoTemplate getMongoTemplate() throws Exception {
        MongoDbFactory factory = mongoDbFactory();
        return new MongoTemplate(factory, getConverter(factory, appContext));
    }
}
