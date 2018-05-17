package com.estone.erp.productdev.wish.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.module.productdev.wish.model.param.CWishProductSearch;
import com.estone.erp.common.mongo.MongoQueryUtils;
import com.estone.erp.common.mongo.Mongos;
import com.estone.erp.productdev.wish.mongo.model.bo.WishProduct;
import com.estone.erp.productdev.wish.mongo.service.IWishProductService;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

/***
 * WishProduct的自定义查询接口实现类
 */
@Service
public class WishProductServiceImpl implements IWishProductService {
    @Qualifier(Mongos.WISH_TEMPLATE)
    @Autowired
    MongoTemplate template;

    @Override
    public Page<WishProduct> getPage(CQuery<CWishProductSearch> cquery) {
        Page<WishProduct> page = MongoQueryUtils.getPage(cquery, search -> {
            return getQuery(search);
        }, template, WishProduct.class);
        return page;
    }

    @Override
    public List<WishProduct> getList(CWishProductSearch productSearch) {
        return MongoQueryUtils.getList(productSearch, search -> {
            return getQuery(search);
        }, template, WishProduct.class);
    }

    private Query getQuery(CWishProductSearch search) {
        if (search == null) {
            return null;
        }

        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotEmpty(search.getPid())) {
            criteria.and("pid").is(search.getPid());
        }
        if (StringUtils.isNotEmpty(search.getPname())) {
            criteria.and("pname").regex(search.getPname());
        }

        MongoQueryUtils.criteriaRange(criteria, "price", search.getMinPrice(), search.getMaxPrice());
        query.addCriteria(criteria);
        return query;
    }
}
