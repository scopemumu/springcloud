package com.estone.erp.common.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询条件
 * 
 * @author Kevin
 *
 * @param <T>
 */
@Data
@ApiModel(value = "查询公共模型")
public class CQuery<T> implements Serializable {
    private static final long serialVersionUID = 3341855372947133412L;

    /**
     * 查询条件
     */
    @ApiModelProperty(value = "查询业务模型，自定义", dataType = "T")
    private T search;

    /**
     * 是否需要分页，默认分页
     */
    @ApiModelProperty(value = "是否需要分页", dataType = "boolean")
    private boolean pageReqired = true;

    /**
     * limit 限定数据量
     */
    private int limit = 20;

    /**
     * 偏移量
     */
    private int offset = 0;

    /**
     * 排序字段
     */
    private String sort;

    /**
     * desc, asc
     */
    private String order;

    public CQuery() {
    }

    public CQuery(boolean pageReqired) {
        this.pageReqired = pageReqired;
    }

    public CQuery(T search, boolean pageReqired) {
        this.search = search;
        this.pageReqired = pageReqired;
    }

    public String getOrderByClause() {
        return getOrderByClause(null);
    }

    public String getOrderByClause(String prefix) {
        if (StringUtils.isEmpty(sort) || StringUtils.isEmpty(order)) {
            return null;
        }

        if (StringUtils.isEmpty(prefix)) {
            return sort + " " + order;
        }
        else {
            return prefix + "." + sort + " " + order;
        }
    }

    public int getPage() {
        int limit = getLimit();
        return offset / limit + 1;
    }
}
