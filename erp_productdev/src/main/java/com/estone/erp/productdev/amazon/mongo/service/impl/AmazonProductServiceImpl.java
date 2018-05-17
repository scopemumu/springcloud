package com.estone.erp.productdev.amazon.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.module.productdev.amazon.model.param.CAmazonProductSearch;
import com.estone.erp.common.mongo.MongoQueryUtils;
import com.estone.erp.common.mongo.Mongos;
import com.estone.erp.productdev.amazon.mongo.model.bo.AmazonProduct;
import com.estone.erp.productdev.amazon.mongo.service.IAmazonProductService;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

/***
 * AmazonProduct的自定义查询接口实现类
 * 
 * @author Kevin
 *
 */
@Service
public class AmazonProductServiceImpl implements IAmazonProductService {
    @Qualifier(Mongos.AMAZON_TEMPLATE)
    @Autowired
    MongoTemplate template;

    @Override
    public Page<AmazonProduct> getPage(CQuery<CAmazonProductSearch> cquery) {
        return MongoQueryUtils.getPage(cquery, search -> {
            Query query = new Query();
            if (StringUtils.isNotEmpty(search.getGoodsId())) {
                query.addCriteria(Criteria.where("goodsId").is(search.getGoodsId()));
            }

            return query;
        }, template, AmazonProduct.class);
    }

    @Override
    public List<AmazonProduct> getList(CAmazonProductSearch productSearch) {
        return MongoQueryUtils.getList(productSearch, search -> {
            Query query = new Query();
            if (StringUtils.isNotEmpty(search.getGoodsId())) {
                query.addCriteria(Criteria.where("goodsId").is(search.getGoodsId()));
            }

            return query;
        }, template, AmazonProduct.class);
    }
}
