package com.estone.erp.usermgt.command.config;

import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.estone.erp.usermgt.command.aggregate.QueueMessageAggregate;

/***
 * 聚合模型Repository的配置文件
 * 
 * @author Kevin
 *
 */
@Configuration
public class AggregateRepositoryConfig {

    @Bean
    public Repository<QueueMessageAggregate> queueMessageRepository(EventStore eventStore) {
        return new EventSourcingRepository<QueueMessageAggregate>(QueueMessageAggregate.class, eventStore);
    }
}
