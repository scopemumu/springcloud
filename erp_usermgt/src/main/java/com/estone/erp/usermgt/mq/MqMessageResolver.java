package com.estone.erp.usermgt.mq;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estone.erp.common.mq.model.MqMessage;
import com.estone.erp.common.util.Logstash;
import com.estone.erp.usermgt.command.command.InsertQueueMessageCommand;
import com.estone.erp.usermgt.mongo.message.model.QueueMessage;

/***
 * 消息队列处理类
 * 
 * @author Kevin
 *
 */
@Component
public class MqMessageResolver {
    @Autowired
    CommandGateway commandGateway;

    /**
     * 处理队列消息
     * 
     * @param mqMessage 消息
     */
    public void resolve(MqMessage mqMessage) {
        Logstash.info(getClass(), mqMessage.toString());
        // TODO:消息处理逻辑
        commandGateway.send(new InsertQueueMessageCommand(new QueueMessage(mqMessage)));
    }
}
