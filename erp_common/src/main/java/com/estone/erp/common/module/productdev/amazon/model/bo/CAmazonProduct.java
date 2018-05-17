package com.estone.erp.common.module.productdev.amazon.model.bo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CAmazonProduct implements Serializable {
    private static final long serialVersionUID = 2922843081780490467L;

    private String id;

    private String category;

    private String goodsSize;

    private String goodsLink;

    private String goodsName;

    private String goodsId;

    private String introduction;

    private Double minPrice;

    private Double maxPrice;

    private String bsrCategorySmall;

    private String imageLink;

    private String inventory;

    private Integer questions;

    private Integer reviewsNum;

    private String goodsColor;

    private String delivery;
}
