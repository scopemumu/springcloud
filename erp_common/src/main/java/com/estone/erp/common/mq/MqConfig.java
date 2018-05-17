package com.estone.erp.common.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.estone.erp.common.mq.Queues;

/**
 * 消息队列配置文件
 * 
 * @author Kevin
 *
 */
@Configuration
public class MqConfig {
    @Bean
    public Queue testQueue() {
        return new Queue(Queues.TEST);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(Queues.USER);
    }
}
