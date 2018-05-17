package com.estone.erp.common.module.productdev.amazon.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.estone.erp.common.model.CQueryResult;
import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.module.Services;
import com.estone.erp.common.module.productdev.amazon.model.bo.CAmazonProduct;
import com.estone.erp.common.module.productdev.amazon.model.param.CAmazonProductSearch;

@FeignClient(value = Services.PRODUCT_DEV)
public interface IAmazonAccountClient {
    @PostMapping(value = "/amazon/products")
    CQueryResult<CAmazonProduct> getByPage(@RequestBody CQuery<CAmazonProductSearch> query);

    @GetMapping(value = "/amazon/product/{id}")
    CAmazonProduct getById(@PathVariable(value = "id") String id);
}
