package com.estone.erp.usermgt.mongo.message.repo;

import javax.annotation.Resource;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.estone.erp.usermgt.mongo.message.model.QueueMessage;

/***
 * QueueMessage的代理查询接口
 * 
 * @author Kevin
 *
 */
@Resource
public interface QueueMessageRepository extends MongoRepository<QueueMessage, String> {

}
