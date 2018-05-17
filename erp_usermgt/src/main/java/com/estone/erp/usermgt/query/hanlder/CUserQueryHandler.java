package com.estone.erp.usermgt.query.hanlder;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estone.erp.common.model.CQuery;
import com.estone.erp.common.model.CQueryResult;
import com.estone.erp.common.module.usermgt.model.param.CUserSearch;
import com.estone.erp.usermgt.base.model.User;
import com.estone.erp.usermgt.base.model.UserExample;
import com.estone.erp.usermgt.base.model.UserExample.Criteria;
import com.estone.erp.usermgt.base.service.IUserService;
import com.estone.erp.usermgt.query.hanlder.constant.UserQuerys;

/***
 * CUser的查询Handler类
 * 
 * @author Kevin
 *
 */
@Component
public class CUserQueryHandler {
    @Autowired
    IUserService userService;

    @QueryHandler(queryName = UserQuerys.ID)
    public User hanlder(Integer id) {
        return userService.getById(id);
    }

    @QueryHandler(queryName = UserQuerys.USER_NAME)
    public User hanlder(String userName) {
        return userService.getByUserName(userName);
    }

    @QueryHandler(queryName = UserQuerys.USER_PERMISSIONURLS)
    public List<String> permissionUrls(String userName) {
        return userService.findPermissionsByUsername(userName);
    }

    @QueryHandler(queryName = UserQuerys.PAGE)
    public CQueryResult<User> hanlder(CQuery<CUserSearch> cquery) {
        CQueryResult<User> result = new CQueryResult<>();
        UserExample example = getUserExample(cquery.getSearch());
        if (cquery.isPageReqired()) {
            result.setTotal(userService.getCount(example));
            example.setLimit(cquery.getLimit());
            example.setOffset(cquery.getOffset());
        }
        example.setOrderByClause(cquery.getOrderByClause());
        result.setRows(userService.get(example));

        return result;
    }

    public UserExample getUserExample(CUserSearch search) {
        UserExample example = new UserExample();
        if (search == null) {
            return example;
        }

        Criteria criteria = example.createCriteria();
        if (search.getId() != null) {
            criteria.andIdEqualTo(search.getId());
        }
        if (StringUtils.isNotEmpty(search.getUserName())) {
            criteria.andUserNameEqualTo(search.getUserName());
        }

        return example;
    }
}
