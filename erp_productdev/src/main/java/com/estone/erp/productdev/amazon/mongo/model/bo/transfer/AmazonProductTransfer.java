package com.estone.erp.productdev.amazon.mongo.model.bo.transfer;

import com.estone.erp.common.module.productdev.amazon.model.bo.CAmazonProduct;
import com.estone.erp.productdev.amazon.mongo.model.bo.AmazonProduct;

public class AmazonProductTransfer {
    public static CAmazonProduct transfer(AmazonProduct product) {
        if (product == null) {
            return null;
        }

        CAmazonProduct cproduct = new CAmazonProduct();
        cproduct.setId(product.getId());
        cproduct.setCategory(product.getCategory());
        cproduct.setGoodsSize(product.getGoodsSize());
        cproduct.setGoodsLink(product.getGoodsLink());
        cproduct.setGoodsName(product.getGoodsName());
        cproduct.setGoodsId(product.getGoodsId());
        cproduct.setIntroduction(product.getIntroduction());
        cproduct.setMinPrice(product.getMinPrice());
        cproduct.setMaxPrice(product.getMaxPrice());
        cproduct.setBsrCategorySmall(product.getBsrCategorySmall());
        cproduct.setImageLink(product.getImageLink());
        cproduct.setQuestions(product.getQuestions());
        cproduct.setReviewsNum(product.getReviewsNum());
        cproduct.setGoodsColor(product.getGoodsColor());
        cproduct.setDelivery(product.getDelivery());

        return cproduct;
    }

    public static AmazonProduct transfer(CAmazonProduct cproduct) {
        if (cproduct == null) {
            return null;
        }

        AmazonProduct product = new AmazonProduct();
        product.setId(cproduct.getId());
        product.setCategory(cproduct.getCategory());
        product.setGoodsSize(cproduct.getGoodsSize());
        product.setGoodsLink(cproduct.getGoodsLink());
        product.setGoodsName(cproduct.getGoodsName());
        product.setGoodsId(cproduct.getGoodsId());
        product.setIntroduction(cproduct.getIntroduction());
        product.setMinPrice(cproduct.getMinPrice());
        product.setMaxPrice(cproduct.getMaxPrice());
        product.setBsrCategorySmall(cproduct.getBsrCategorySmall());
        product.setImageLink(cproduct.getImageLink());
        product.setQuestions(cproduct.getQuestions());
        product.setReviewsNum(cproduct.getReviewsNum());
        product.setGoodsColor(cproduct.getGoodsColor());
        product.setDelivery(cproduct.getDelivery());

        return product;
    }
}
