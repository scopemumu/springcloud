package com.estone.erp.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.collections.CollectionUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * 查询结果类
 * 
 * @author Kevin
 *
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CQueryResult<T> extends ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 4246916039752643968L;

    /**
     * 总数
     */
    private long total;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 返回值-集合
     */
    private List<T> rows;

    public CQueryResult() {
    }

    public CQueryResult(List<T> rows) {
        this.rows = rows;
    }

    public CQueryResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public CQueryResult(long total, int totalPages, List<T> rows) {
        this.total = total;
        this.totalPages = totalPages;
        this.rows = rows;
    }

    public <R> CQueryResult(CQueryResult<R> result, Function<R, T> fun) {
        if (result == null) {
            return;
        }

        this.total = result.getTotal();
        this.totalPages = result.getTotalPages();
        List<R> resultRows = result.getRows();
        if (CollectionUtils.isEmpty(resultRows)) {
            this.rows = new ArrayList<>(0);
            return;
        }

        this.rows = new ArrayList<>(resultRows.size());
        for (R r : resultRows) {
            rows.add(fun.apply(r));
        }
    }
}
