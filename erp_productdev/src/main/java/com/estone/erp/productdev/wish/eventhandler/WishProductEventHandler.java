package com.estone.erp.productdev.wish.eventhandler;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estone.erp.common.module.productdev.wish.model.bo.CWishProduct;
import com.estone.erp.productdev.wish.event.WishProductUpdateEvent;
import com.estone.erp.productdev.wish.mongo.model.bo.WishProduct;
import com.estone.erp.productdev.wish.mongo.repo.WishProductRepository;

/***
 * Wish商品eventhandler
 */
@Component
public class WishProductEventHandler {
    @Autowired
    private WishProductRepository wishProductRepository;

    @EventHandler
    public void wishProductUpdate(WishProductUpdateEvent event) {
        CWishProduct cwishProduct = event.getWishProductUpdateCommand().getCwishProduct();
        WishProduct wishProduct = wishProductRepository.findOne(cwishProduct.getId());
        wishProduct.setPname(cwishProduct.getPname());
        wishProduct.setPrice(cwishProduct.getPrice());

        wishProductRepository.save(wishProduct);
    }
}
