package com.estone.erp.common.module.productdev.wish.model.param;

import java.io.Serializable;

import com.estone.erp.common.module.productdev.wish.model.bo.CWishProduct;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CWishProductSearch extends CWishProduct implements Serializable {
    private static final long serialVersionUID = 2812016146007215017L;

    private String minApprovedDate;

    private String maxApprovedDate;

    private Integer minNumBought;

    private Integer maxNumBought;

    private Integer minNumEntered;

    private Integer maxNumEntered;

    private Integer minNumRating;

    private Integer maxNumRating;

    private String minGenTime;

    private String maxGenTime;

    private Double minOPrice;

    private Double maxOPrice;

    private Double minOShipping;

    private Double maxOShipping;

    private Double minPrice;

    private Double maxPrice;

    private Double minShipping;

    private Double maxShipping;
}
