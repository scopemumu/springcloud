package com.estone.erp.usermgt.base.model.transfer;

import com.estone.erp.common.module.usermgt.model.bo.CUser;
import com.estone.erp.usermgt.base.model.User;

/***
 * User和外部模型CUser的转换类
 * 
 * @author Kevin
 *
 */
public class UserTransfer {
    /**
     * 将User转换为CUser
     * 
     * @param user user
     * @return CUser
     */
    public static CUser transfer(User user) {
        if (user == null) {
            return null;
        }

        CUser cuser = new CUser();
        cuser.setId(user.getId());
        cuser.setUserName(user.getUserName());
        cuser.setImaccount(user.getImaccount());
        cuser.setEmployee(user.getEmployee());
        cuser.setManager(user.getManager());
        cuser.setInactive(user.getInactive());
        cuser.setGroupName(user.getGroupName());
        cuser.setIsShowVendor(user.getIsShowVendor());
        cuser.setPlatformGroupId(user.getPlatformGroupId());
        cuser.setLastLoginTime(user.getLastLoginTime());

        return cuser;
    }

    /**
     * 将CUser转换为User
     * 
     * @param cuser cuser
     * @return User
     */
    public static User transfer(CUser cuser) {
        if (cuser == null) {
            return null;
        }

        User user = new User();
        user.setId(cuser.getId());
        user.setPassword(cuser.getPassword());
        user.setUserName(cuser.getUserName());
        user.setEncryptedPassword(cuser.getEncryptedPassword());
        user.setEncryptedUserName(cuser.getEncryptedUserName());
        user.setImaccount(cuser.getImaccount());
        user.setEmployee(cuser.getEmployee());
        user.setManager(cuser.getManager());
        user.setInactive(cuser.getInactive());
        user.setGroupName(cuser.getGroupName());
        user.setIsShowVendor(cuser.getIsShowVendor());
        user.setPlatformGroupId(cuser.getPlatformGroupId());
        user.setLastLoginTime(cuser.getLastLoginTime());

        return user;
    }
}
