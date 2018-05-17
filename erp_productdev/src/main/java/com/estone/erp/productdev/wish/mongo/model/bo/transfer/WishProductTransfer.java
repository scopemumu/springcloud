package com.estone.erp.productdev.wish.mongo.model.bo.transfer;

import com.estone.erp.common.module.productdev.wish.model.bo.CWishMerTag;
import com.estone.erp.common.module.productdev.wish.model.bo.CWishMerchantTag;
import com.estone.erp.common.module.productdev.wish.model.bo.CWishProTag;
import com.estone.erp.common.module.productdev.wish.model.bo.CWishProduct;
import com.estone.erp.common.module.productdev.wish.model.bo.CWishSku;
import com.estone.erp.common.module.productdev.wish.model.bo.CWishTag;
import com.estone.erp.common.util.CommonUtils;
import com.estone.erp.productdev.wish.mongo.model.bo.WishMerTag;
import com.estone.erp.productdev.wish.mongo.model.bo.WishMerchantTag;
import com.estone.erp.productdev.wish.mongo.model.bo.WishProTag;
import com.estone.erp.productdev.wish.mongo.model.bo.WishProduct;
import com.estone.erp.productdev.wish.mongo.model.bo.WishSku;
import com.estone.erp.productdev.wish.mongo.model.bo.WishTag;

public class WishProductTransfer {
    public static CWishProduct transfer(WishProduct product) {
        if (product == null) {
            return null;
        }

        CWishProduct cproduct = new CWishProduct();

        cproduct.setId(product.getId());
        cproduct.setPid(product.getPid());
        cproduct.setPname(product.getPname());
        cproduct.setMid(product.getMid());
        cproduct.setApprovedDate(product.getApprovedDate());
        cproduct.setIsPromo(product.getIsPromo());
        cproduct.setIsVerified(product.getIsVerified());
        cproduct.setIsHWC(product.getIsHWC());
        cproduct.setNumBought(product.getNumBought());
        cproduct.setNumEntered(product.getNumEntered());
        cproduct.setNumRating(product.getNumRating());
        cproduct.setRating(product.getRating());
        cproduct.setGenTime(product.getGenTime());
        cproduct.setOprice(product.getOprice());
        cproduct.setOshipping(product.getOshipping());
        cproduct.setPrice(product.getPrice());
        cproduct.setShipping(product.getShipping());
        cproduct.setHotflag(product.getHotflag());
        cproduct.setMerchant(product.getMerchant());

        cproduct.setMerTags(CommonUtils.transfer(product.getMerTags(), wishMerTag -> {
            CWishMerTag cWishMerTag = new CWishMerTag();
            cWishMerTag.setId(wishMerTag.getId());
            cWishMerTag.setName(wishMerTag.getName());
            cWishMerTag.setIsFilter(wishMerTag.getIsFilter());
            return cWishMerTag;
        }));

        cproduct.setProTags(CommonUtils.transfer(product.getProTags(), wishProTag -> {
            CWishProTag cWishProTag = new CWishProTag();
            cWishProTag.setId(wishProTag.getId());
            cWishProTag.setName(wishProTag.getName());
            cWishProTag.setIsFilter(wishProTag.getIsFilter());
            return cWishProTag;
        }));

        cproduct.setTotalPrice(product.getTotalPrice());
        cproduct.setSupplierUrl(product.getSupplierUrl());
        cproduct.setParentSku(product.getParentSku());
        cproduct.setTitle(product.getTitle());
        cproduct.setSpicture(product.getSpicture());
        cproduct.setBrand(product.getBrand());

        cproduct.setMerchantTags(CommonUtils.transfer(product.getMerchantTags(), wishProTag -> {
            CWishMerchantTag cWishMerchantTag = new CWishMerchantTag();
            cWishMerchantTag.setId(wishProTag.getId());
            cWishMerchantTag.setName(wishProTag.getName());
            cWishMerchantTag.setIsFilter(wishProTag.getIsFilter());
            return cWishMerchantTag;
        }));

        cproduct.setTags(CommonUtils.transfer(product.getTags(), wishTag -> {
            CWishTag cWishTag = new CWishTag();
            cWishTag.setId(wishTag.getId());
            cWishTag.setName(wishTag.getName());
            cWishTag.setIsFilter(wishTag.getIsFilter());
            return cWishTag;
        }));

        cproduct.setDescription(product.getDescription());
        cproduct.setMinShipTime(product.getMinShipTime());
        cproduct.setMaxShipTime(product.getMaxShipTime());
        cproduct.setExtraPhotos(product.getExtraPhotos());

        cproduct.setSkus(CommonUtils.transfer(product.getSkus(), wishSku -> {
            CWishSku cWishSku = new CWishSku();
            cWishSku.setSubSku(wishSku.getSubSku());
            cWishSku.setColor(wishSku.getColor());
            cWishSku.setSize(wishSku.getSize());
            cWishSku.setSize(wishSku.getSize());
            cWishSku.setRprice(wishSku.getRprice());
            cWishSku.setOprice(wishSku.getOprice());
            cWishSku.setOshipping(wishSku.getOshipping());
            return cWishSku;
        }));

        return cproduct;
    }

    public static WishProduct transfer(CWishProduct cproduct) {
        if (cproduct == null) {
            return null;
        }

        WishProduct product = new WishProduct();

        product.setId(cproduct.getId());
        product.setPid(cproduct.getPid());
        product.setPname(cproduct.getPname());
        product.setMid(cproduct.getMid());
        product.setApprovedDate(cproduct.getApprovedDate());
        product.setIsPromo(cproduct.getIsPromo());
        product.setIsVerified(cproduct.getIsVerified());
        product.setIsHWC(cproduct.getIsHWC());
        product.setNumBought(cproduct.getNumBought());
        product.setNumEntered(cproduct.getNumEntered());
        product.setNumRating(cproduct.getNumRating());
        product.setRating(cproduct.getRating());
        product.setGenTime(cproduct.getGenTime());
        product.setOprice(cproduct.getOprice());
        product.setOshipping(cproduct.getOshipping());
        product.setPrice(cproduct.getPrice());
        product.setShipping(cproduct.getShipping());
        product.setHotflag(cproduct.getHotflag());
        product.setMerchant(cproduct.getMerchant());

        product.setMerTags(CommonUtils.transfer(cproduct.getMerTags(), cWishMerTag -> {
            WishMerTag wishMerTag = new WishMerTag();
            wishMerTag.setId(cWishMerTag.getId());
            wishMerTag.setName(cWishMerTag.getName());
            wishMerTag.setIsFilter(cWishMerTag.getIsFilter());

            return wishMerTag;
        }));

        product.setProTags(CommonUtils.transfer(cproduct.getProTags(), cWishProTag -> {
            WishProTag wishProTag = new WishProTag();
            wishProTag.setId(cWishProTag.getId());
            wishProTag.setName(cWishProTag.getName());
            wishProTag.setIsFilter(cWishProTag.getIsFilter());

            return wishProTag;
        }));

        product.setTotalPrice(cproduct.getTotalPrice());
        product.setSupplierUrl(cproduct.getSupplierUrl());
        product.setParentSku(cproduct.getParentSku());
        product.setTitle(cproduct.getTitle());
        product.setSpicture(cproduct.getSpicture());
        product.setBrand(cproduct.getBrand());

        product.setMerchantTags(CommonUtils.transfer(cproduct.getMerchantTags(), cWishProTag -> {
            WishMerchantTag wishMerchantTag = new WishMerchantTag();
            wishMerchantTag.setId(cWishProTag.getId());
            wishMerchantTag.setName(cWishProTag.getName());
            wishMerchantTag.setIsFilter(cWishProTag.getIsFilter());

            return wishMerchantTag;
        }));

        product.setTags(CommonUtils.transfer(cproduct.getTags(), cWishTag -> {
            WishTag wishTag = new WishTag();
            wishTag.setId(cWishTag.getId());
            wishTag.setName(cWishTag.getName());
            wishTag.setIsFilter(cWishTag.getIsFilter());

            return wishTag;
        }));

        product.setDescription(cproduct.getDescription());
        product.setMinShipTime(cproduct.getMinShipTime());
        product.setMaxShipTime(cproduct.getMaxShipTime());
        product.setExtraPhotos(cproduct.getExtraPhotos());

        product.setSkus(CommonUtils.transfer(cproduct.getSkus(), cWishSku -> {
            WishSku wishSku = new WishSku();
            wishSku.setSubSku(cWishSku.getSubSku());
            wishSku.setColor(cWishSku.getColor());
            wishSku.setSize(cWishSku.getSize());
            wishSku.setSize(cWishSku.getSize());
            wishSku.setRprice(cWishSku.getRprice());
            wishSku.setOprice(cWishSku.getOprice());
            wishSku.setOshipping(cWishSku.getOshipping());

            return wishSku;
        }));

        return product;
    }
}
