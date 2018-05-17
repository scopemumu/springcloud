package com.estone.erp.usermgt.command.aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.estone.erp.usermgt.command.command.InsertQueueMessageCommand;
import com.estone.erp.usermgt.command.event.InsertQueueMessageEvent;

/***
 * QueueMessage的聚合类
 * 
 * @author Kevin
 *
 */
@Aggregate
public class QueueMessageAggregate {
    @AggregateIdentifier
    private String identifier;

    public QueueMessageAggregate() {

    }

    /**
     * 插入QueueMessage的命令Handler
     * 
     * @param command 命令
     */
    @CommandHandler
    public QueueMessageAggregate(InsertQueueMessageCommand command) {
        this.identifier = command.getIdentifier();
        apply(new InsertQueueMessageEvent(command));
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
