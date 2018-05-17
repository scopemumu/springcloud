package com.estone.erp.web.productdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.model.CQueryResult;
import com.estone.erp.common.module.productdev.amazon.client.IAmazonAccountClient;
import com.estone.erp.common.module.productdev.amazon.model.bo.CAmazonProduct;
import com.estone.erp.common.module.productdev.amazon.model.param.CAmazonProductSearch;
import com.estone.erp.common.module.usermgt.client.IUserServiceClient;

@Controller
@RequestMapping("/amazon")
public class AmazonProductController {

    @Autowired
    IAmazonAccountClient amazonAccountClient;

    @Autowired
    private IUserServiceClient iUserServiceClient;

    @PostMapping(value = "/products")
    @ResponseBody
    public CQueryResult<CAmazonProduct> get(CQuery<CAmazonProductSearch> cquery) {
        cquery = new CQuery<>(true);

        return amazonAccountClient.getByPage(cquery);
    }

    @GetMapping(value = "/product/{id}")
    public CAmazonProduct getById(@PathVariable(value = "id") String id) {
        return amazonAccountClient.getById(id);
    }

    @GetMapping(value = "/page")
    public String getUser() {
        return "product/amazon";
    }
}
