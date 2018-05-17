package com.estone.erp.common.module.usermgt.fallback;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.model.CQueryResult;
import com.estone.erp.common.module.usermgt.client.IUserServiceClient;
import com.estone.erp.common.module.usermgt.model.bo.CUser;
import com.estone.erp.common.module.usermgt.model.param.CUserSearch;

/***
 * IUserServiceClient的接口错误处理类
 * 
 * @author Kevin
 *
 */
@Component
public class UserServiceFallBack implements IUserServiceClient {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public CUser getUserById(Integer id) {
        logger.info("exception occured, method({}), params: id = {}", "getUserById", id);
        return getErrorCUser();
    }

    @Override
    public CUser getUserByUserName(String userName) {
        logger.info("exception occured, method({}), params: userName = {}", "getUserById", userName);
        return getErrorCUser();
    }

    private CUser getErrorCUser() {
        CUser user = new CUser();
        user.setId(-1);
        user.setUserName("default username");
        return user;
    }

    @Override
    public List<String> findPermissionsByUsername(String userName) {
        List<String> list = new ArrayList<>(0);
        logger.info("exception occured, method({}), params: userName = {}", "findPermissionsByUsername", userName);
        return list;
    }

    @Override
    public CQueryResult<CUser> getUsersByPage(CQuery<CUserSearch> cquery) {
        logger.info("exception occured, method({}), params: cquery = {}", "getUsersByPage", cquery);
        return new CQueryResult<>();
    }
}
