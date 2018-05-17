package com.estone.erp.productdev.wish.mongo.model.bo;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class WishTag {
    @Field(value = "id")
    private String id;

    @Field(value = "name")
    private String name;

    @Field(value = "is_filter")
    private Boolean isFilter;
}
