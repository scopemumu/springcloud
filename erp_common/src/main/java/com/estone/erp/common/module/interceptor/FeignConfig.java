package com.estone.erp.common.module.interceptor;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * FeignClient发送请求前, 拦截请求头或者请求体中的token, 设置进头信息
 */
@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            HttpServletRequest request = getHttpServletRequest();
            if (StringUtils.isNotBlank(getHeaders(request).get("authorization"))) {
                requestTemplate.header("authorization", getHeaders(request).get("authorization"));
            }
            else if (StringUtils
                    .isNotBlank(getHeaders(request).get("OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE"))) {
                requestTemplate.header("authorization",
                        "bearer " + getHeaders(request).get("OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE"));
            }
        }
        catch (Exception e) {
            throw new RuntimeException("拦截器获取请求参数信息出错");
        }
    }

    private HttpServletRequest getHttpServletRequest() throws Exception {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        while (attributeNames.hasMoreElements()) {
            String key = attributeNames.nextElement();
            if (request.getAttribute(key) instanceof String) {
                String value = (String) request.getAttribute(key);
                map.put(key, value);
            }
        }

        return map;
    }
}
