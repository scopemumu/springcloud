package com.estone.erp.productdev.amazon.mongo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.estone.erp.productdev.amazon.mongo.model.bo.AmazonProduct;

/***
 * AmazonProduct的代理查询接口
 * 
 * @author Kevin
 *
 */
public interface AmazonProductRepository extends MongoRepository<AmazonProduct, String> {
    List<AmazonProduct> findByGoodsId(String goodsId);
}
