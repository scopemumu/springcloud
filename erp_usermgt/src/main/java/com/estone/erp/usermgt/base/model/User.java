package com.estone.erp.usermgt.base.model;

import java.util.Date;

public class User {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword == null ? null : encryptedPassword.trim();
    }

    public String getEncryptedUserName() {
        return encryptedUserName;
    }

    public void setEncryptedUserName(String encryptedUserName) {
        this.encryptedUserName = encryptedUserName == null ? null : encryptedUserName.trim();
    }

    public String getImaccount() {
        return imaccount;
    }

    public void setImaccount(String imaccount) {
        this.imaccount = imaccount == null ? null : imaccount.trim();
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public Integer getManager() {
        return manager;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Boolean getIsShowVendor() {
        return isShowVendor;
    }

    public void setIsShowVendor(Boolean isShowVendor) {
        this.isShowVendor = isShowVendor;
    }

    public Integer getPlatformGroupId() {
        return platformGroupId;
    }

    public void setPlatformGroupId(Integer platformGroupId) {
        this.platformGroupId = platformGroupId;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}