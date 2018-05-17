package com.estone.erp.productdev.amazon.mongo.model.bo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/***
 * amazon产品
 * 
 * @author Kevin
 *
 */
@Data
@Document(collection = "data")
public class AmazonProduct implements Serializable {
    private static final long serialVersionUID = -8058544406328963565L;

    @Id
    private String id;

    @Field(value = "category")
    private String category;

    @Field(value = "goods_size")
    private String goodsSize;

    @Field(value = "goods_link")
    private String goodsLink;

    @Field(value = "goods_name")
    private String goodsName;

    @Field(value = "goods_id")
    private String goodsId;

    @Field(value = "introduction")
    private String introduction;

    @Field(value = "minPrice")
    private Double minPrice;

    @Field(value = "maxPrice")
    private Double maxPrice;

    @Field(value = "bsr_category_small")
    private String bsrCategorySmall;

    @Field(value = "image_link")
    private String imageLink;

    @Field(value = "inventory")
    private String inventory;

    @Field(value = "questions")
    private Integer questions;

    @Field(value = "reviews_num")
    private Integer reviewsNum;

    @Field(value = "goods_color")
    private String goodsColor;

    @Field(value = "delivery")
    private String delivery;
}
