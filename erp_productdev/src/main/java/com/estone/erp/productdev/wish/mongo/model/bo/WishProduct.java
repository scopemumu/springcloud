package com.estone.erp.productdev.wish.mongo.model.bo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/***
 * wish产品
 */
@Data
@Document(collection = "pro_infos")
public class WishProduct implements Serializable {
    private static final long serialVersionUID = 7598860923684775341L;

    @Id
    private String id;

    @Field(value = "pid")
    /** 商品id */
    private String pid;

    @Field(value = "pname")
    /** 商品名称 */
    private String pname;

    @Field(value = "mid")
    /** 商家ID */
    private String mid;

    @Field(value = "approved_date")
    /** 店铺开张时间 */
    private String approvedDate;

    @Field(value = "is_promo")
    /** 是否加钻促销 */
    private Integer isPromo;

    @Field(value = "is_verified")
    /** 是否wish认证 */
    private Integer isVerified;

    @Field(value = "is_HWC")
    /** 是否海外仓 */
    private Integer isHWC;

    @Field(value = "num_bought")
    /** 总购买人数 */
    private Integer numBought;

    @Field(value = "num_entered")
    /** 总收藏人数 */
    private Integer numEntered;

    @Field(value = "num_rating")
    /** 总评论人数 */
    private Integer numRating;

    @Field(value = "rating")
    /** 评分 */
    private Double rating;

    @Field(value = "gen_time")
    /** 上架时间 */
    private String genTime;

    @Field(value = "o_price")
    /** 商家定价 */
    private Double oprice;

    @Field(value = "o_shipping")
    /** 商家运费 */
    private Double oshipping;

    @Field(value = "price")
    /** wish售价 */
    private Double price;

    @Field(value = "shipping")
    /** wish运费 */
    private Double shipping;

    @Field(value = "hotflag")
    /** 爆品标记 */
    private Integer hotflag;

    @Field(value = "merchant")
    /** 店铺名称 */
    private String merchant;

    @Field(value = "mer_tags")
    private List<WishMerTag> merTags;

    @Field(value = "pro_tags")
    private List<WishProTag> proTags;

    @Field(value = "c_ids")
    /** 商品所属类目id */
    private List<String> cids;

    @Field(value = "totalprice")
    private Double totalPrice;

    @Field(value = "supplier_url")
    /** 是否有供应商 */
    private Integer supplierUrl;

    @Field(value = "parentSku")
    /** 父sku */
    private String parentSku;

    @Field(value = "title")
    /** 标题 */
    private String title;

    @Field(value = "s_picture")
    /** 主图 */
    private String spicture;

    @Field(value = "brand")
    /** 品牌 */
    private String brand;

    @Field(value = "merchant_tags")
    /** wish标签 */
    private List<WishMerchantTag> merchantTags;

    @Field(value = "tags")
    /** 商家自填标签 */
    private List<WishTag> tags;

    @Field(value = "description")
    /** 商品描述 */
    private String description;

    @Field(value = "min_ship_time")
    /** 最小运输时间 */
    private Integer minShipTime;

    @Field(value = "max_ship_time")
    /** 最长运输时间 */
    private Integer maxShipTime;

    @Field(value = "extra_photos")
    /** 商品图片 */
    private List<String> extraPhotos;

    @Field(value = "skus")
    /** 商品的所有sku */
    private List<WishSku> skus;
}
