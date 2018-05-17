package com.estone.erp.usermgt.base.service;

import java.util.List;

import com.estone.erp.usermgt.base.model.User;
import com.estone.erp.usermgt.base.model.UserExample;

public interface IUserService {

    public List<User> get(UserExample example);

    public User getById(Integer id);

    public long getCount(UserExample example);

    public void updateByPrimaryKey(User record);

    public User getByUserName(String userName);

    public User getByLogin(String userName, String password);
    
    public User findUserPermissionByUsername(String userName);

    List<String> findPermissionsByUsername(String username);
    
}
