package com.estone.erp.web.productdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.model.CQueryResult;
import com.estone.erp.common.module.productdev.wish.client.IWishAccountClient;
import com.estone.erp.common.module.productdev.wish.model.bo.CWishProduct;
import com.estone.erp.common.module.productdev.wish.model.param.CWishProductSearch;

@Controller
@RequestMapping("/wish")
public class WishProductController {

    @Autowired
    IWishAccountClient wishAccountClient;

    @PostMapping(value = "/products")
    @ResponseBody
    public CQueryResult<CWishProduct> get(@RequestBody CQuery<CWishProductSearch> cquery) {
        CQueryResult<CWishProduct> byPage = wishAccountClient.getByPage(cquery);
        return byPage;
    }

    @GetMapping(value = "/product/{id}")
    public CWishProduct getById(@PathVariable(value = "id") String id) {
        return wishAccountClient.getById(id);
    }

    @GetMapping(value = "/page")
    public String getUser() {
        return "product/wishProduct";
    }
}
