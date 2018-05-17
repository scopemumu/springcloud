package com.estone.erp.productdev.amazon.mongo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.module.productdev.amazon.model.param.CAmazonProductSearch;
import com.estone.erp.productdev.amazon.mongo.model.bo.AmazonProduct;

/***
 * AmazonProduct的自定义查询接口
 * 
 * @author Kevin
 *
 */
public interface IAmazonProductService {
    /**
     * 获取分页数据
     * 
     * @param cquery 查询参数
     * @return 分页模型
     */
    Page<AmazonProduct> getPage(CQuery<CAmazonProductSearch> cquery);

    /**
     * 根据条件查询数据
     * 
     * @param search 查询条件
     * @return
     */
    List<AmazonProduct> getList(CAmazonProductSearch search);
}
