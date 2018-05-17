package com.estone.erp.productdev.wish.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.axonframework.common.IdentifierFactory;

import com.estone.erp.common.module.productdev.wish.model.bo.CWishProduct;

import lombok.Data;

/***
 * 单个Wish商品修改command
 */
@Data
public class WishProductUpdateCommand {
    @TargetAggregateIdentifier
    private String identifier;

    private CWishProduct cwishProduct;

    public WishProductUpdateCommand(CWishProduct cwishProduct) {
        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
        this.cwishProduct = cwishProduct;
    }
}
