package com.estone.erp.productdev.wish.query;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.module.productdev.wish.model.param.CWishProductSearch;
import com.estone.erp.productdev.wish.mongo.model.bo.WishProduct;
import com.estone.erp.productdev.wish.mongo.repo.WishProductRepository;
import com.estone.erp.productdev.wish.mongo.service.IWishProductService;
import com.estone.erp.productdev.wish.query.constant.WishProductQuerys;

@Component
public class WishProductQueryHandler {
    @Autowired
    WishProductRepository repository;

    @Autowired
    IWishProductService wishProductService;

    @QueryHandler(queryName = WishProductQuerys.ID)
    public WishProduct getById(String id) {
        return repository.findOne(id);
    }

    @QueryHandler(queryName = WishProductQuerys.PAGE)
    public Page<WishProduct> on(CQuery<CWishProductSearch> query) {
        return wishProductService.getPage(query);
    }

    @QueryHandler(queryName = WishProductQuerys.CRITERIA)
    public List<WishProduct> getByCriteria(CWishProductSearch search) {
        return wishProductService.getList(search);
    }
}
