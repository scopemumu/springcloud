package com.estone.erp.productdev.smt.mongo.model.bo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/***
 * smt产品
 * 
 * @author Kevin
 *
 */
@Data
@Document(collection = "data")
public class SmtProduct implements Serializable {
    private static final long serialVersionUID = -206783508137650882L;

    @Id
    private String id;

    @Field(value = "Class")
    private String classType;

    @Field(value = "Subclass")
    private String subClass;

    @Field(value = "Classification")
    private String classification;

    @Field(value = "Store_opening")
    private String storeOpening;

    @Field(value = "Store_name")
    private String storeName;

    @Field(value = "Store_link")
    private String storeLink;

    @Field(value = "Picture")
    private String picture;

    @Field(value = "Commodity_name")
    private String commodityName;

    @Field(value = "Commodity_link")
    private String commodityLink;

    @Field(value = "Commentaries_number")
    private String commentariesNumber;

    @Field(value = "Commodity_grade")
    private String commodityGrade;

    @Field(value = "Sales_volume")
    private String salesVolume;

    @Field(value = "Currency")
    private String currency;

    @Field(value = "Commodity_type")
    private List<CommodityType> CommodityTypes;
}
