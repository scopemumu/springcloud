package com.estone.erp.common.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.message")
public class MessageCommonConfig extends AbstractMongoConfig {

    @Autowired
    private ApplicationContext appContext;

    @Override
    @Primary
    @Bean(name = Mongos.MESSAGE_TEMPLATE)
    public MongoTemplate getMongoTemplate() throws Exception {
        MongoDbFactory factory = mongoDbFactory();
        return new MongoTemplate(factory, getConverter(factory, appContext));
    }

    @Bean(name = Mongos.MESSAGE_CLIENT)
    public MongoClient getMongoClient() {
        return mongoClientFactory();
    }
}
