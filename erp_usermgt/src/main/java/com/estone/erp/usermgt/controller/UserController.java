package com.estone.erp.usermgt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.model.CQueryResult;
import com.estone.erp.common.module.usermgt.model.bo.CUser;
import com.estone.erp.common.module.usermgt.model.param.CUserSearch;
import com.estone.erp.common.util.FutureUtils;
import com.estone.erp.common.util.RedisUtils;
import com.estone.erp.usermgt.base.model.User;
import com.estone.erp.usermgt.base.model.transfer.UserTransfer;
import com.estone.erp.usermgt.query.hanlder.constant.UserQuerys;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@SuppressWarnings("unchecked")
public class UserController {
    @Autowired
    private QueryGateway queryGateway;

    @SuppressWarnings("unused")
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @PostMapping(value = "/users")
    @ApiOperation(value = "获取多个用户", notes = "根据查询参数获取用户")
    @ApiParam(name = "cquery", value = "用户查询模型", required = true)
    public CQueryResult<CUser> getUsers(@RequestBody(required = false) CQuery<CUserSearch> cquery) {
        if (cquery == null) {
            cquery = new CQuery<>();
        }
        CQueryResult<User> result = FutureUtils.get(queryGateway.send(cquery, UserQuerys.PAGE, CQueryResult.class));
        return new CQueryResult<>(result, user -> {
            return UserTransfer.transfer(user);
        });
    }

    @GetMapping(value = "/user/{id}")
    @ApiOperation(value = "获取用户", notes = "根据id获取用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer")
    public CUser getUser(@PathVariable(value = "id") Integer id, HttpServletResponse response) {
        CUser user = UserTransfer.transfer(FutureUtils.get(queryGateway.send(id, UserQuerys.ID, User.class)));
        return user;
    }

    @GetMapping(value = "/user")
    @ApiOperation(value = "获取用户", notes = "根据用户名获取用户")
    @ApiParam(name = "userName", value = "用户名", required = true)
    public CUser getUser(@RequestParam(value = "userName") String userName, HttpServletResponse response) {
        CUser user = UserTransfer
                .transfer(FutureUtils.get(queryGateway.send(userName, UserQuerys.USER_NAME, User.class)));
        log.warn("query user: userName = {}", userName);
        return user;
    }

    @GetMapping(value = "/permission/urls")
    @ApiOperation(value = "获取用户权限", notes = "根据用户名获取用户权限")
    @ApiParam(name = "userName", value = "用户名", required = true)
    public List<String> permissionUrls(@RequestParam(value = "userName") String userName) {
        List<String> permissions = FutureUtils
                .get(queryGateway.send(userName, UserQuerys.USER_PERMISSIONURLS, List.class));
        if (permissions.isEmpty()) {
            return permissions;
        }
        return permissions;
    }

    @GetMapping("/user/logout")
    @ApiOperation(value = "登出", notes = "退出登录")
    @ResponseBody
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 清除登录数据
        String name = authentication.getName();
        if (StringUtils.isNotBlank(name)) {
            RedisUtils.del(name);
        }
    }
}
