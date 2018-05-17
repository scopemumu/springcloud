package com.estone.erp.usermgt.command.hanlder;

import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estone.erp.usermgt.command.command.InsertQueueMessageCommand;
import com.estone.erp.usermgt.command.event.InsertQueueMessageEvent;
import com.estone.erp.usermgt.mongo.message.model.QueueMessage;
import com.estone.erp.usermgt.mongo.message.repo.QueueMessageRepository;

/***
 * QueueMessage的事件Handler类
 * 
 * @author Kevin
 *
 */
@Component
public class QueueMessageEventHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    QueueMessageRepository repository;

    @EventHandler
    public void on(InsertQueueMessageEvent event) {
        InsertQueueMessageCommand command = event.getCommand();
        QueueMessage queueMessage = command.getQueueMessage();
        logger.info("insert queueMessage: {}", queueMessage);
        repository.insert(queueMessage);
    }
}
