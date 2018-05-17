package com.estone.erp.common.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estone.erp.common.mq.model.MqMessage;

/***
 * 消息队列发送组件
 * 
 * @author Kevin
 *
 */
@Component
public class MqSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送消息队列
     * 
     * @param queue 队列名
     * @param mqMessage 队列消息
     */
    public void send(String queue, MqMessage mqMessage) {
        mqMessage.setQueue(queue);
        amqpTemplate.convertAndSend(queue, mqMessage);
    }
}
