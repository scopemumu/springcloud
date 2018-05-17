package com.estone.erp.productdev.amazon.controller;

import java.util.List;

import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.model.CQueryResult;
import com.estone.erp.common.module.Services;
import com.estone.erp.common.module.productdev.amazon.model.bo.CAmazonProduct;
import com.estone.erp.common.module.productdev.amazon.model.param.CAmazonProductSearch;
import com.estone.erp.common.module.usermgt.client.IUserServiceClient;
import com.estone.erp.common.module.usermgt.model.bo.CUser;
import com.estone.erp.common.mq.MqSender;
import com.estone.erp.common.mq.Queues;
import com.estone.erp.common.mq.model.MqMessage;
import com.estone.erp.common.mq.model.MqType;
import com.estone.erp.common.util.CommonUtils;
import com.estone.erp.common.util.FutureUtils;
import com.estone.erp.productdev.amazon.mongo.model.bo.AmazonProduct;
import com.estone.erp.productdev.amazon.mongo.model.bo.transfer.AmazonProductTransfer;
import com.estone.erp.productdev.amazon.query.constant.AmazonProductQuerys;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/***
 * 负责AmazonProduct的URL请求
 * 
 * @author Kevin
 *
 */
@RestController
@RequestMapping(value = "/amazon")
@SuppressWarnings("unchecked")
public class AmazonProductController {
    @Autowired
    private QueryGateway queryGateway;

    @Autowired
    private IUserServiceClient userServiceClient;

    @Autowired
    private MqSender mqSender;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public CUser getUser() {
        CUser user = userServiceClient.getUserById(337);
        MqMessage msg = new MqMessage(MqType.IGNORE, Services.PRODUCT_DEV, "System", JSON.toJSONString(user));
        mqSender.send(Queues.TEST, msg);
        return user;
    }

    @PostMapping(value = "/products")
    @ApiOperation(value = "查询amazon产品", notes = "根据条件查询amazon产品")
    @ApiParam(name = "cquery", value = "查询条件", required = false)
    public CQueryResult<CAmazonProduct> get(@RequestBody(required = false) CQuery<CAmazonProductSearch> cquery) {
        if (cquery.isPageReqired()) {
            Page<AmazonProduct> page = FutureUtils.get(queryGateway.send(cquery, AmazonProductQuerys.PAGE, Page.class));
            List<CAmazonProduct> cproducts = CommonUtils.transfer(page.getContent(), product -> {
                return AmazonProductTransfer.transfer(product);
            });

            return new CQueryResult<>(page.getTotalElements(), page.getTotalPages(), cproducts);
        }
        else {
            CAmazonProductSearch search = cquery.getSearch();
            List<AmazonProduct> list = FutureUtils.get(queryGateway.send(
                    search == null ? new CAmazonProductSearch() : search, AmazonProductQuerys.CRITERIA, List.class));
            return new CQueryResult<>(CommonUtils.transfer(list, product -> {
                return AmazonProductTransfer.transfer(product);
            }));
        }
    }

    @GetMapping(value = "/product/{id}")
    public CAmazonProduct getById(@PathVariable(value = "id") String id) {
        AmazonProduct product = FutureUtils.get(queryGateway.send(id, AmazonProductQuerys.ID, AmazonProduct.class));
        return AmazonProductTransfer.transfer(product);
    }
}
