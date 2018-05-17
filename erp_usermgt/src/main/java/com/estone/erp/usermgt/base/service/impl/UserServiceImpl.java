package com.estone.erp.usermgt.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.estone.erp.usermgt.base.mapper.UserMapper;
import com.estone.erp.usermgt.base.mapper.custom.CustomUserMapper;
import com.estone.erp.usermgt.base.model.User;
import com.estone.erp.usermgt.base.model.UserExample;
import com.estone.erp.usermgt.base.model.UserExample.Criteria;
import com.estone.erp.usermgt.base.service.IUserService;

/***
 * User的Service接口实现类
 * 
 * @author Kevin
 *
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private CustomUserMapper customUserMapper;

    @Override
    public List<User> get(UserExample example) {
        return userMapper.selectByExample(example);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public long getCount(UserExample example) {
        return userMapper.countByExample(example);
    }

    @Override
    public void updateByPrimaryKey(User record) {
        userMapper.updateByPrimaryKey(record);
    }

    @Override
    public User getByUserName(String userName) {
        UserExample example = new UserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<User> result = get(example);

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public User getByLogin(String userName, String password) {
        UserExample example = new UserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        criteria.andPasswordEqualTo(password);
        List<User> result = get(example);

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<String> findPermissionsByUsername(String username) {
        return customUserMapper.findPermissionsByUsername(username);
    }

    @Override
    public User findUserPermissionByUsername(String userName) {
        // TODO Auto-generated method stub
        return null;
    }
}
