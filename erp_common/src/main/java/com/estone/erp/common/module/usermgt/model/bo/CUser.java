package com.estone.erp.common.module.usermgt.model.bo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/***
 * 用户管理-User的对外模型
 * 
 * @author Kevin
 *
 */
@Data
public class CUser implements Serializable {
    private static final long serialVersionUID = -5444889991350903673L;

    private Integer id;

    private String password;

    private String userName;

    private String encryptedPassword;

    private String encryptedUserName;

    private String imaccount;

    private Integer employee;

    private Integer manager;

    private Boolean inactive;

    private String groupName;

    private Boolean isShowVendor;

    private Integer platformGroupId;

    private Date lastLoginTime;
}
