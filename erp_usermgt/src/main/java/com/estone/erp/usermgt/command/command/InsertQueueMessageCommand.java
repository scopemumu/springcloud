package com.estone.erp.usermgt.command.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.axonframework.common.IdentifierFactory;

import com.estone.erp.usermgt.mongo.message.model.QueueMessage;

/***
 * 插入QueueMessage的命令类
 * 
 * @author Kevin
 *
 */
public class InsertQueueMessageCommand {
    @TargetAggregateIdentifier
    private String identifier;

    private QueueMessage queueMessage;

    public InsertQueueMessageCommand() {

    }

    public InsertQueueMessageCommand(QueueMessage queueMessage) {
        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
        this.queueMessage = queueMessage;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public QueueMessage getQueueMessage() {
        return queueMessage;
    }

    public void setQueueMessage(QueueMessage queueMessage) {
        this.queueMessage = queueMessage;
    }
}
