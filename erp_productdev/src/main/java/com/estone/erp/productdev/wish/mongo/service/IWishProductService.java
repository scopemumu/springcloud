package com.estone.erp.productdev.wish.mongo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.module.productdev.wish.model.param.CWishProductSearch;
import com.estone.erp.productdev.wish.mongo.model.bo.WishProduct;

/***
 * WishProduct的自定义查询接口
 */
public interface IWishProductService {
    /**
     * 获取分页数据
     * 
     * @param cquery 查询参数
     * @return 分页模型
     */
    Page<WishProduct> getPage(CQuery<CWishProductSearch> cquery);

    /**
     * 根据条件查询数据
     * 
     * @param search 查询条件
     * @return
     */
    List<WishProduct> getList(CWishProductSearch search);
}
