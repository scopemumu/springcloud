package com.estone.erp.productdev.wish.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.estone.erp.productdev.wish.mongo.model.bo.WishProduct;

/***
 * WishProduct的代理查询接口
 * 
 * @author Kevin
 *
 */
@Repository
public interface WishProductRepository extends MongoRepository<WishProduct, String> {

}
