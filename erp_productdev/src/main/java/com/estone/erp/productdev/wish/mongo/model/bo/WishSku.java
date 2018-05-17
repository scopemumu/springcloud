package com.estone.erp.productdev.wish.mongo.model.bo;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class WishSku {
    @Field(value = "subSku")
    private String subSku;

    @Field(value = "color")
    private String color;

    @Field(value = "size")
    private String size;

    @Field(value = "r_price")
    private Double rprice;

    @Field(value = "o_price")
    private Double oprice;

    @Field(value = "o_shipping")
    private Double oshipping;
}
