package com.estone.erp.usermgt.base.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("userName is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("userName is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("userName =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("userName <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("userName >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("userName >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("userName <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("userName <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("userName like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("userName not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("userName in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("userName not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("userName between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("userName not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andEncryptedPasswordIsNull() {
            addCriterion("encryptedPassword is null");
            return (Criteria) this;
        }

        public Criteria andEncryptedPasswordIsNotNull() {
            addCriterion("encryptedPassword is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptedPasswordEqualTo(String value) {
            addCriterion("encryptedPassword =", value, "encryptedPassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedPasswordNotEqualTo(String value) {
            addCriterion("encryptedPassword <>", value, "encryptedPassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedPasswordGreaterThan(String value) {
            addCriterion("encryptedPassword >", value, "encryptedPassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("encryptedPassword >=", value, "encryptedPassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedPasswordLessThan(String value) {
            addCriterion("encryptedPassword <", value, "encryptedPassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedPasswordLessThanOrEqualTo(String value) {
            addCriterion("encryptedPassword <=", value, "encryptedPassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedPasswordLike(String value) {
            addCriterion("encryptedPassword like", value, "encryptedPassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedPasswordNotLike(String value) {
            addCriterion("encryptedPassword not like", value, "encryptedPassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedPasswordIn(List<String> values) {
            addCriterion("encryptedPassword in", values, "encryptedPassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedPasswordNotIn(List<String> values) {
            addCriterion("encryptedPassword not in", values, "encryptedPassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedPasswordBetween(String value1, String value2) {
            addCriterion("encryptedPassword between", value1, value2, "encryptedPassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedPasswordNotBetween(String value1, String value2) {
            addCriterion("encryptedPassword not between", value1, value2, "encryptedPassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedUserNameIsNull() {
            addCriterion("encryptedUserName is null");
            return (Criteria) this;
        }

        public Criteria andEncryptedUserNameIsNotNull() {
            addCriterion("encryptedUserName is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptedUserNameEqualTo(String value) {
            addCriterion("encryptedUserName =", value, "encryptedUserName");
            return (Criteria) this;
        }

        public Criteria andEncryptedUserNameNotEqualTo(String value) {
            addCriterion("encryptedUserName <>", value, "encryptedUserName");
            return (Criteria) this;
        }

        public Criteria andEncryptedUserNameGreaterThan(String value) {
            addCriterion("encryptedUserName >", value, "encryptedUserName");
            return (Criteria) this;
        }

        public Criteria andEncryptedUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("encryptedUserName >=", value, "encryptedUserName");
            return (Criteria) this;
        }

        public Criteria andEncryptedUserNameLessThan(String value) {
            addCriterion("encryptedUserName <", value, "encryptedUserName");
            return (Criteria) this;
        }

        public Criteria andEncryptedUserNameLessThanOrEqualTo(String value) {
            addCriterion("encryptedUserName <=", value, "encryptedUserName");
            return (Criteria) this;
        }

        public Criteria andEncryptedUserNameLike(String value) {
            addCriterion("encryptedUserName like", value, "encryptedUserName");
            return (Criteria) this;
        }

        public Criteria andEncryptedUserNameNotLike(String value) {
            addCriterion("encryptedUserName not like", value, "encryptedUserName");
            return (Criteria) this;
        }

        public Criteria andEncryptedUserNameIn(List<String> values) {
            addCriterion("encryptedUserName in", values, "encryptedUserName");
            return (Criteria) this;
        }

        public Criteria andEncryptedUserNameNotIn(List<String> values) {
            addCriterion("encryptedUserName not in", values, "encryptedUserName");
            return (Criteria) this;
        }

        public Criteria andEncryptedUserNameBetween(String value1, String value2) {
            addCriterion("encryptedUserName between", value1, value2, "encryptedUserName");
            return (Criteria) this;
        }

        public Criteria andEncryptedUserNameNotBetween(String value1, String value2) {
            addCriterion("encryptedUserName not between", value1, value2, "encryptedUserName");
            return (Criteria) this;
        }

        public Criteria andImaccountIsNull() {
            addCriterion("imaccount is null");
            return (Criteria) this;
        }

        public Criteria andImaccountIsNotNull() {
            addCriterion("imaccount is not null");
            return (Criteria) this;
        }

        public Criteria andImaccountEqualTo(String value) {
            addCriterion("imaccount =", value, "imaccount");
            return (Criteria) this;
        }

        public Criteria andImaccountNotEqualTo(String value) {
            addCriterion("imaccount <>", value, "imaccount");
            return (Criteria) this;
        }

        public Criteria andImaccountGreaterThan(String value) {
            addCriterion("imaccount >", value, "imaccount");
            return (Criteria) this;
        }

        public Criteria andImaccountGreaterThanOrEqualTo(String value) {
            addCriterion("imaccount >=", value, "imaccount");
            return (Criteria) this;
        }

        public Criteria andImaccountLessThan(String value) {
            addCriterion("imaccount <", value, "imaccount");
            return (Criteria) this;
        }

        public Criteria andImaccountLessThanOrEqualTo(String value) {
            addCriterion("imaccount <=", value, "imaccount");
            return (Criteria) this;
        }

        public Criteria andImaccountLike(String value) {
            addCriterion("imaccount like", value, "imaccount");
            return (Criteria) this;
        }

        public Criteria andImaccountNotLike(String value) {
            addCriterion("imaccount not like", value, "imaccount");
            return (Criteria) this;
        }

        public Criteria andImaccountIn(List<String> values) {
            addCriterion("imaccount in", values, "imaccount");
            return (Criteria) this;
        }

        public Criteria andImaccountNotIn(List<String> values) {
            addCriterion("imaccount not in", values, "imaccount");
            return (Criteria) this;
        }

        public Criteria andImaccountBetween(String value1, String value2) {
            addCriterion("imaccount between", value1, value2, "imaccount");
            return (Criteria) this;
        }

        public Criteria andImaccountNotBetween(String value1, String value2) {
            addCriterion("imaccount not between", value1, value2, "imaccount");
            return (Criteria) this;
        }

        public Criteria andEmployeeIsNull() {
            addCriterion("employee is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIsNotNull() {
            addCriterion("employee is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeEqualTo(Integer value) {
            addCriterion("employee =", value, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeNotEqualTo(Integer value) {
            addCriterion("employee <>", value, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeGreaterThan(Integer value) {
            addCriterion("employee >", value, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("employee >=", value, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeLessThan(Integer value) {
            addCriterion("employee <", value, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeLessThanOrEqualTo(Integer value) {
            addCriterion("employee <=", value, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeIn(List<Integer> values) {
            addCriterion("employee in", values, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeNotIn(List<Integer> values) {
            addCriterion("employee not in", values, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeBetween(Integer value1, Integer value2) {
            addCriterion("employee between", value1, value2, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeNotBetween(Integer value1, Integer value2) {
            addCriterion("employee not between", value1, value2, "employee");
            return (Criteria) this;
        }

        public Criteria andManagerIsNull() {
            addCriterion("manager is null");
            return (Criteria) this;
        }

        public Criteria andManagerIsNotNull() {
            addCriterion("manager is not null");
            return (Criteria) this;
        }

        public Criteria andManagerEqualTo(Integer value) {
            addCriterion("manager =", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotEqualTo(Integer value) {
            addCriterion("manager <>", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerGreaterThan(Integer value) {
            addCriterion("manager >", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerGreaterThanOrEqualTo(Integer value) {
            addCriterion("manager >=", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerLessThan(Integer value) {
            addCriterion("manager <", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerLessThanOrEqualTo(Integer value) {
            addCriterion("manager <=", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerIn(List<Integer> values) {
            addCriterion("manager in", values, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotIn(List<Integer> values) {
            addCriterion("manager not in", values, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerBetween(Integer value1, Integer value2) {
            addCriterion("manager between", value1, value2, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotBetween(Integer value1, Integer value2) {
            addCriterion("manager not between", value1, value2, "manager");
            return (Criteria) this;
        }

        public Criteria andInactiveIsNull() {
            addCriterion("inactive is null");
            return (Criteria) this;
        }

        public Criteria andInactiveIsNotNull() {
            addCriterion("inactive is not null");
            return (Criteria) this;
        }

        public Criteria andInactiveEqualTo(Boolean value) {
            addCriterion("inactive =", value, "inactive");
            return (Criteria) this;
        }

        public Criteria andInactiveNotEqualTo(Boolean value) {
            addCriterion("inactive <>", value, "inactive");
            return (Criteria) this;
        }

        public Criteria andInactiveGreaterThan(Boolean value) {
            addCriterion("inactive >", value, "inactive");
            return (Criteria) this;
        }

        public Criteria andInactiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("inactive >=", value, "inactive");
            return (Criteria) this;
        }

        public Criteria andInactiveLessThan(Boolean value) {
            addCriterion("inactive <", value, "inactive");
            return (Criteria) this;
        }

        public Criteria andInactiveLessThanOrEqualTo(Boolean value) {
            addCriterion("inactive <=", value, "inactive");
            return (Criteria) this;
        }

        public Criteria andInactiveIn(List<Boolean> values) {
            addCriterion("inactive in", values, "inactive");
            return (Criteria) this;
        }

        public Criteria andInactiveNotIn(List<Boolean> values) {
            addCriterion("inactive not in", values, "inactive");
            return (Criteria) this;
        }

        public Criteria andInactiveBetween(Boolean value1, Boolean value2) {
            addCriterion("inactive between", value1, value2, "inactive");
            return (Criteria) this;
        }

        public Criteria andInactiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("inactive not between", value1, value2, "inactive");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("groupName is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("groupName is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("groupName =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("groupName <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("groupName >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("groupName >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("groupName <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("groupName <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("groupName like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("groupName not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("groupName in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("groupName not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("groupName between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("groupName not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andIsShowVendorIsNull() {
            addCriterion("isShowVendor is null");
            return (Criteria) this;
        }

        public Criteria andIsShowVendorIsNotNull() {
            addCriterion("isShowVendor is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowVendorEqualTo(Boolean value) {
            addCriterion("isShowVendor =", value, "isShowVendor");
            return (Criteria) this;
        }

        public Criteria andIsShowVendorNotEqualTo(Boolean value) {
            addCriterion("isShowVendor <>", value, "isShowVendor");
            return (Criteria) this;
        }

        public Criteria andIsShowVendorGreaterThan(Boolean value) {
            addCriterion("isShowVendor >", value, "isShowVendor");
            return (Criteria) this;
        }

        public Criteria andIsShowVendorGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isShowVendor >=", value, "isShowVendor");
            return (Criteria) this;
        }

        public Criteria andIsShowVendorLessThan(Boolean value) {
            addCriterion("isShowVendor <", value, "isShowVendor");
            return (Criteria) this;
        }

        public Criteria andIsShowVendorLessThanOrEqualTo(Boolean value) {
            addCriterion("isShowVendor <=", value, "isShowVendor");
            return (Criteria) this;
        }

        public Criteria andIsShowVendorIn(List<Boolean> values) {
            addCriterion("isShowVendor in", values, "isShowVendor");
            return (Criteria) this;
        }

        public Criteria andIsShowVendorNotIn(List<Boolean> values) {
            addCriterion("isShowVendor not in", values, "isShowVendor");
            return (Criteria) this;
        }

        public Criteria andIsShowVendorBetween(Boolean value1, Boolean value2) {
            addCriterion("isShowVendor between", value1, value2, "isShowVendor");
            return (Criteria) this;
        }

        public Criteria andIsShowVendorNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isShowVendor not between", value1, value2, "isShowVendor");
            return (Criteria) this;
        }

        public Criteria andPlatformGroupIdIsNull() {
            addCriterion("platformGroupId is null");
            return (Criteria) this;
        }

        public Criteria andPlatformGroupIdIsNotNull() {
            addCriterion("platformGroupId is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformGroupIdEqualTo(Integer value) {
            addCriterion("platformGroupId =", value, "platformGroupId");
            return (Criteria) this;
        }

        public Criteria andPlatformGroupIdNotEqualTo(Integer value) {
            addCriterion("platformGroupId <>", value, "platformGroupId");
            return (Criteria) this;
        }

        public Criteria andPlatformGroupIdGreaterThan(Integer value) {
            addCriterion("platformGroupId >", value, "platformGroupId");
            return (Criteria) this;
        }

        public Criteria andPlatformGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("platformGroupId >=", value, "platformGroupId");
            return (Criteria) this;
        }

        public Criteria andPlatformGroupIdLessThan(Integer value) {
            addCriterion("platformGroupId <", value, "platformGroupId");
            return (Criteria) this;
        }

        public Criteria andPlatformGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("platformGroupId <=", value, "platformGroupId");
            return (Criteria) this;
        }

        public Criteria andPlatformGroupIdIn(List<Integer> values) {
            addCriterion("platformGroupId in", values, "platformGroupId");
            return (Criteria) this;
        }

        public Criteria andPlatformGroupIdNotIn(List<Integer> values) {
            addCriterion("platformGroupId not in", values, "platformGroupId");
            return (Criteria) this;
        }

        public Criteria andPlatformGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("platformGroupId between", value1, value2, "platformGroupId");
            return (Criteria) this;
        }

        public Criteria andPlatformGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("platformGroupId not between", value1, value2, "platformGroupId");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIsNull() {
            addCriterion("lastLoginTime is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIsNotNull() {
            addCriterion("lastLoginTime is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeEqualTo(Date value) {
            addCriterion("lastLoginTime =", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotEqualTo(Date value) {
            addCriterion("lastLoginTime <>", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThan(Date value) {
            addCriterion("lastLoginTime >", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lastLoginTime >=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThan(Date value) {
            addCriterion("lastLoginTime <", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThanOrEqualTo(Date value) {
            addCriterion("lastLoginTime <=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIn(List<Date> values) {
            addCriterion("lastLoginTime in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotIn(List<Date> values) {
            addCriterion("lastLoginTime not in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeBetween(Date value1, Date value2) {
            addCriterion("lastLoginTime between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotBetween(Date value1, Date value2) {
            addCriterion("lastLoginTime not between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}