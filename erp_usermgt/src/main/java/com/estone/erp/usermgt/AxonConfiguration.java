package com.estone.erp.usermgt;

import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.documentperevent.DocumentPerEventStorageStrategy;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;

/***
 * axon配置文件
 * 
 * @author Kevin
 *
 */
@Configuration
public class AxonConfiguration {
    @Value("${axon.amqp.exchange}")
    private String exchangeName;

    @Value("${spring.data.mongodb.message.database}")
    private String database;

    @Qualifier(value = "messageMongoClient")
    @Autowired
    MongoClient mongoClient;

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange(exchangeName).durable(true).build();
    }

    @Bean(name = "eventStorageEngine")
    public EventStorageEngine eventStorageEngine(Serializer serializer) {
        return new MongoEventStorageEngine(serializer, null, new DefaultMongoTemplate(mongoClient, database),
                new DocumentPerEventStorageStrategy());
    }

    @Bean(name = "sagaStore")
    public SagaStore<?> sagaStore(Serializer serializer) {
        return new MongoSagaStore(new DefaultMongoTemplate(mongoClient, database), serializer);
    }
}
