package com.estone.erp.productdev.ebay.mongo.model.bo;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/**
 * ebay商品类型
 * 
 * @author Kevin
 *
 */
@Data
public class CommodityType {
    @Field(value = "Attribute_one")
    private String attrOne;

    @Field(value = "Attribute_one_item")
    private String attrOneItem;

    @Field(value = "Attribute_two")
    private String attrTwo;

    @Field(value = "Attribute_two_item")
    private String attrTwoItem;

    @Field(value = "Attribute_three")
    private String attrThree;

    @Field(value = "Attribute_three_item")
    private String attrThreeItem;

    @Field(value = "Attribute_four")
    private String attrFour;

    @Field(value = "Attribute_four_item")
    private String attrFourItem;

    @Field(value = "Sales_inventory")
    private Integer salesInventory;

    @Field(value = "Watching")
    private Integer watching;

    @Field(value = "Discount_price")
    private String discountPrice;

    @Field(value = "Original_price")
    private String originalPrice;

    @Field(value = "Commodity_id")
    private String commodityId;
}
