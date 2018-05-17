package com.estone.erp.productdev.amazon.query;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.module.productdev.amazon.model.param.CAmazonProductSearch;
import com.estone.erp.productdev.amazon.mongo.model.bo.AmazonProduct;
import com.estone.erp.productdev.amazon.mongo.repo.AmazonProductRepository;
import com.estone.erp.productdev.amazon.mongo.service.IAmazonProductService;
import com.estone.erp.productdev.amazon.query.constant.AmazonProductQuerys;

@Component
public class AmazonProductQueryHandler {
    @Autowired
    AmazonProductRepository repository;

    @Autowired
    IAmazonProductService amazonProductService;

    @QueryHandler(queryName = AmazonProductQuerys.ID)
    public AmazonProduct getById(String id) {
        return repository.findOne(id);
    }

    @QueryHandler(queryName = AmazonProductQuerys.PAGE)
    public Page<AmazonProduct> on(CQuery<CAmazonProductSearch> query) {
        return amazonProductService.getPage(query);
    }

    @QueryHandler(queryName = AmazonProductQuerys.CRITERIA)
    public List<AmazonProduct> getByCriteria(CAmazonProductSearch search) {
        return amazonProductService.getList(search);
    }
}
