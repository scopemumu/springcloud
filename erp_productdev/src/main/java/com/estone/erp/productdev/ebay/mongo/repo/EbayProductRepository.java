package com.estone.erp.productdev.ebay.mongo.repo;

import javax.annotation.Resource;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.estone.erp.productdev.ebay.mongo.model.bo.EbayProduct;

/***
 * EbayProduct的代理查询接口
 * 
 * @author Kevin
 *
 */
@Resource
public interface EbayProductRepository extends MongoRepository<EbayProduct, String> {

}
