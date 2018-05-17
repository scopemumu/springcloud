package com.estone.erp.productdev.ebay.mongo.model.bo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/***
 * ebay产品
 * 
 * @author Kevin
 *
 */
@Data
public class EbayProduct implements Serializable {
    private static final long serialVersionUID = -4999161084325466511L;

    @Id
    private String id;

    @Field(value = "Picture")
    private String picture;

    @Field(value = "Commodity_name")
    private String commodityName;

    @Field(value = "Commodity_link")
    private String commodityLink;

    @Field(value = "Total_watching")
    private String totalWatching;

    @Field(value = "Sales_volume")
    private String salesVolume;

    @Field(value = "Class")
    private String classType;

    @Field(value = "Subclass")
    private String subclass;

    @Field(value = "Classification")
    private String classification;

    @Field(value = "Store_opening")
    private String storeOpening;

    @Field(value = "Store_name")
    private String storeName;

    @Field(value = "Store_link")
    private String storeLink;

    @Field(value = "Commodity_type")
    private List<CommodityType> CommodityTypes;
}
