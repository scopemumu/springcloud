package com.estone.erp.common.module.usermgt.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.model.CQueryResult;
import com.estone.erp.common.module.Services;
import com.estone.erp.common.module.usermgt.fallback.UserServiceFallBack;
import com.estone.erp.common.module.usermgt.model.bo.CUser;
import com.estone.erp.common.module.usermgt.model.param.CUserSearch;

/***
 * 用户管理-IUserService服务接口
 * 
 * @author Kevin
 *
 */
@FeignClient(value = Services.USER_MGT, fallback = UserServiceFallBack.class)
public interface IUserServiceClient {
    @GetMapping(value = "/user/{id}")
    CUser getUserById(@PathVariable("id") Integer id);

    @GetMapping(value = "/user")
    CUser getUserByUserName(@RequestParam(value = "userName") String userName);

    @PostMapping(value = "/users")
    CQueryResult<CUser> getUsersByPage(@RequestBody CQuery<CUserSearch> cquery);
    
    @GetMapping(value = "/permission/urls")
    List<String> findPermissionsByUsername(@RequestParam(value = "userName") String userName);
}