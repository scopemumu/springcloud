package com.estone.erp.common.module.productdev.wish.model.bo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CWishProduct implements Serializable {
    private static final long serialVersionUID = 4140872633920977826L;

    private String id;

    private String pid;

    private String pname;

    private String mid;

    private String approvedDate;

    private Integer isPromo;

    private Integer isVerified;

    private Integer isHWC;

    private Integer numBought;

    private Integer numEntered;

    private Integer numRating;

    private Double rating;

    private String genTime;

    private Double oprice;

    private Double oshipping;

    private Double price;

    private Double shipping;

    private Integer hotflag;

    private String merchant;

    private List<CWishMerTag> merTags;

    private List<CWishProTag> proTags;

    private List<String> cIds;

    private Double totalPrice;

    private Integer supplierUrl;

    private String parentSku;

    private String title;

    private String spicture;

    private String brand;

    private List<CWishMerchantTag> merchantTags;

    private List<CWishTag> tags;

    private String description;

    private Integer minShipTime;

    private Integer maxShipTime;

    private List<String> extraPhotos;

    private List<CWishSku> skus;
}
