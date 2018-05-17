package com.estone.erp.common.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.estone.erp.common.model.CQuery;

/***
 * mongo查询工具类
 * 
 * @author Kevin
 *
 */
public class MongoQueryUtils {
    private static final Logger logger = LoggerFactory.getLogger(MongoQueryUtils.class);

    /**
     * 分页查询
     * 
     * @param t 查询模型
     * @param queryFun 查询处理方法，返回Query
     * @param pager 分页
     * @param template mongoTemplate
     * @param clazz 实体class
     * @return 分页数据
     */
    public static <T, R> Page<R> getPage(CQuery<T> cquery, Function<T, Query> queryFun, MongoTemplate template,
            Class<R> clazz) {
        T t = cquery.getSearch();
        if (!checkArgs(t, queryFun, template)) {
            return null;
        }

        Pageable pageable = new PageRequest(cquery.getPage() - 1, cquery.getLimit());
        Query query = t == null ? null : queryFun.apply(t);
        long count = template.count(query, clazz);
        if (query == null) {
            query = new Query();
        }
        List<R> list = template.find(query.with(pageable), clazz);

        return new PageImpl<R>(list, pageable, count);
    }

    /**
     * 集合查询
     * 
     * @param t 查询模型
     * @param queryFun 查询处理方法，返回Query
     * @param template mongoTemplate
     * @param clazz 实体class
     * @return 集合数据
     */
    public static <T, R> List<R> getList(T t, Function<T, Query> queryFun, MongoTemplate template, Class<R> clazz) {
        if (!checkArgs(t, queryFun, template)) {
            return new ArrayList<R>(0);
        }

        return template.find(t == null ? null : queryFun.apply(t), clazz);
    }

    /**
     * 单个查询
     * 
     * @param t 查询模型
     * @param queryFun 查询处理方法，返回Query
     * @param template mongoTemplate
     * @return 单个数据
     */
    public static <T> T getOne(T t, Function<T, Query> queryFun, MongoTemplate template) {
        if (!checkArgs(t, queryFun, template)) {
            return null;
        }

        return template.findOne(t == null ? null : queryFun.apply(t), getClass(t));
    }

    /**
     * 单个查询
     * 
     * @param t 查询模型
     * @param queryFun 查询处理方法，返回Query
     * @param template mongoTemplate
     * @param clazz 实体class
     * @return 单个数据
     */
    public static <T, R> R getOne(T t, Function<T, Query> queryFun, MongoTemplate template, Class<R> clazz) {
        if (!checkArgs(t, queryFun, template)) {
            return null;
        }

        return template.findOne(t == null ? null : queryFun.apply(t), clazz);
    }

    private static <T> boolean checkArgs(T t, Function<T, Query> queryFun, MongoTemplate template) {
        Asserts.notNull(template, "MongoTemplate");
        if (t != null && queryFun == null) {
            logger.warn("this arg[queryFun] is required; it must not be null.");
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClass(T t) {
        Class<T> clazz = (Class<T>) t.getClass();
        return clazz;
    }

    public static Criteria criteriaRange(Criteria criteria, String key, Object lt, Object gt) {
        if (criteria == null || StringUtils.isEmpty(key)) {
            return criteria;
        }

        if (lt != null) {
            criteria = criteria.and(key).gte(lt);
            if (gt != null) {
                criteria.lt(gt);
            }
        }
        else if (gt != null) {
            criteria.and(key).lt(gt);
        }

        return criteria;
    }
}
