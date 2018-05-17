package com.estone.erp.usermgt.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estone.erp.common.mq.Queues;
import com.estone.erp.common.mq.model.MqMessage;

/**
 * 消息队列接收组件
 * 
 * @author Kevin
 *
 */
@Component
@RabbitListener(queues = { Queues.TEST })
public class MqReciever {

    @Autowired
    MqMessageResolver mqMessageResolver;

    /**
     * 接收队列消息
     * 
     * @param mqMessage 队列消息
     */
    @RabbitHandler
    public void recieve(MqMessage mqMessage) {
        mqMessageResolver.resolve(mqMessage);
    }
}
