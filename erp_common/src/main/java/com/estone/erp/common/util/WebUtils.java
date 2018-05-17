package com.estone.erp.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;

import com.alibaba.druid.util.StringUtils;
import com.estone.erp.common.config.ERPCommonConfig;

/***
 * web工具类
 * 
 * @author Kevin
 *
 */
public class WebUtils {
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    /**
     * 判断request是否为ajax请求
     * 
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("accept").indexOf("application/json") > -1
                || (request.getHeader("content-type") != null
                        && request.getHeader("content-type").indexOf("application/json") > -1))
                || (request.getHeader("X-Requested-With") != null
                        && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1);
    }

    /**
     * 获取request的http状态码
     * 
     * @param request
     * @return
     */
    public static HttpStatus getHttpStatus(HttpServletRequest request) {
        Assert.notNull(request, "HttpServletRequest is reqired not null.");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public static boolean ignoreToken(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }

        ERPCommonConfig erpCommonConfig = SpringUtils.getBean(ERPCommonConfig.class);
        String[] ignoreTokenUrls = erpCommonConfig.getIgnoreTokenUrls();
        if (ArrayUtils.isEmpty(ignoreTokenUrls)) {
            return false;
        }

        for (String ignoreTokenUrl : ignoreTokenUrls) {
            if (ANT_PATH_MATCHER.match(ignoreTokenUrl, url)) {
                return true;
            }
        }

        return false;
    }
}
