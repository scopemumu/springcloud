package com.estone.erp.productdev.wish.config;

import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.estone.erp.productdev.wish.aggregate.WishProductAggregate;

/***
 * 聚合模型Repository的配置文件
 */
@Configuration
public class AggregateRepositoryConfig {

    @Bean
    public Repository<WishProductAggregate> queueMessageRepository(EventStore eventStore) {
        return new EventSourcingRepository<WishProductAggregate>(WishProductAggregate.class, eventStore);
    }
}
