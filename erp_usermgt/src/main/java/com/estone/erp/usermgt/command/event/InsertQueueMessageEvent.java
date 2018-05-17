package com.estone.erp.usermgt.command.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import com.estone.erp.usermgt.command.command.InsertQueueMessageCommand;

/***
 * 插入QueueMessage的事件类
 * 
 * @author Kevin
 *
 */
public class InsertQueueMessageEvent {
    @TargetAggregateIdentifier
    private String identifier;

    private InsertQueueMessageCommand command;

    public InsertQueueMessageEvent() {

    }

    public InsertQueueMessageEvent(InsertQueueMessageCommand command) {
        this.identifier = command.getIdentifier();
        this.command = command;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public InsertQueueMessageCommand getCommand() {
        return command;
    }

    public void setCommand(InsertQueueMessageCommand command) {
        this.command = command;
    }
}
