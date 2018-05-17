package com.estone.erp.productdev.wish.controller;

import java.util.List;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estone.erp.common.model.ApiResult;
import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.model.CQueryResult;
import com.estone.erp.common.module.productdev.amazon.model.param.CAmazonProductSearch;
import com.estone.erp.common.module.productdev.wish.model.bo.CWishProduct;
import com.estone.erp.common.module.productdev.wish.model.param.CWishProductSearch;
import com.estone.erp.common.util.CommonUtils;
import com.estone.erp.common.util.FutureUtils;
import com.estone.erp.productdev.wish.command.WishProductUpdateCommand;
import com.estone.erp.productdev.wish.mongo.model.bo.WishProduct;
import com.estone.erp.productdev.wish.mongo.model.bo.transfer.WishProductTransfer;
import com.estone.erp.productdev.wish.query.constant.WishProductQuerys;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/***
 * 负责WishProduct的URL请求
 */
@RestController
@RequestMapping(value = "/wish")
@SuppressWarnings("unchecked")
public class WishProductController {
    @Autowired
    private QueryGateway queryGateway;

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(value = "/products")
    @ApiOperation(value = "查询wish产品", notes = "根据条件查询wish产品")
    @ApiParam(name = "cquery", value = "查询条件", required = true)
    public CQueryResult<CWishProduct> get(@RequestBody CQuery<CWishProductSearch> cquery) {
        if (cquery.isPageReqired()) {
            Page<WishProduct> page = FutureUtils.get(queryGateway.send(cquery, WishProductQuerys.PAGE, Page.class));
            List<CWishProduct> cproducts = null;
            if (null != page) {
                cproducts = CommonUtils.transfer(page.getContent(), product -> {
                    return WishProductTransfer.transfer(product);
                });
            }

            return new CQueryResult<>(page.getTotalElements(), page.getTotalPages(), cproducts);
        }
        else {
            CWishProductSearch search = cquery.getSearch();
            List<WishProduct> list = FutureUtils.get(queryGateway.send(
                    search == null ? new CAmazonProductSearch() : search, WishProductQuerys.CRITERIA, List.class));
            return new CQueryResult<>(CommonUtils.transfer(list, product -> {
                return WishProductTransfer.transfer(product);
            }));
        }
    }

    @GetMapping(value = "/product/{id}")
    public ApiResult<CWishProduct> getById(@PathVariable(value = "id") String id) {
        WishProduct product = FutureUtils.get(queryGateway.send(id, WishProductQuerys.ID, WishProduct.class));
        return ApiResult.newSuccess(WishProductTransfer.transfer(product));
    }

    @PostMapping(value = "/product/update")
    public void UpdateProduct(@RequestBody CWishProduct cWishProduct) {
        commandGateway.send(new WishProductUpdateCommand(cWishProduct));
    }
}
