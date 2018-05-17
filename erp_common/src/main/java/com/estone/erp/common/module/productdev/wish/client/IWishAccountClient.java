package com.estone.erp.common.module.productdev.wish.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.model.CQueryResult;
import com.estone.erp.common.module.Services;
import com.estone.erp.common.module.productdev.wish.model.bo.CWishProduct;
import com.estone.erp.common.module.productdev.wish.model.param.CWishProductSearch;

@FeignClient(value = Services.PRODUCT_DEV)
public interface IWishAccountClient {
    @PostMapping(value = "/wish/products")
    CQueryResult<CWishProduct> getByPage(@RequestBody CQuery<CWishProductSearch> query);

    @GetMapping(value = "/wish/product/{id}")
    CWishProduct getById(@PathVariable(value = "id") String id);
}
