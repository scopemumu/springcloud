package com.estone.erp.productdev.wish.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import com.estone.erp.productdev.wish.command.WishProductUpdateCommand;

import lombok.Data;

/***
 * 单个Wish商品修改command
 */
@Data
public class WishProductUpdateEvent {
    @TargetAggregateIdentifier
    private String identifier;

    private WishProductUpdateCommand wishProductUpdateCommand;

    public WishProductUpdateEvent(WishProductUpdateCommand wishProductUpdateCommand) {
        this.identifier = wishProductUpdateCommand.getIdentifier();
        this.wishProductUpdateCommand = wishProductUpdateCommand;
    }
}
