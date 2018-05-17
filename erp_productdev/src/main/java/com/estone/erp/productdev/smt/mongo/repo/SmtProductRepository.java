package com.estone.erp.productdev.smt.mongo.repo;

import javax.annotation.Resource;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.estone.erp.productdev.smt.mongo.model.bo.SmtProduct;

/***
 * SmtProduct的代理查询接口
 * 
 * @author Kevin
 *
 */
@Resource
public interface SmtProductRepository extends MongoRepository<SmtProduct, String> {

}
