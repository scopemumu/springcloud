package com.estone.erp.common.security.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import com.alibaba.fastjson.JSON;
import com.estone.erp.common.model.TokenData;
import com.estone.erp.common.security.properties.SecurityProperties;
import com.estone.erp.common.util.RedisUtils;
import com.estone.erp.common.util.SpringUtils;
import com.estone.erp.common.util.WebUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

/**
 * 拦截名为access_token的cookie, 设置进头信息的过滤器
 */
@Slf4j
public class PreFilter implements Filter {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String accessToken = request.getHeader("Authorization");
        boolean isAjaxRequest = WebUtils.isAjaxRequest(request);
        if (StringUtils.isEmpty(accessToken)) {
            if (isAjaxRequest && !WebUtils.ignoreToken(request.getRequestURI())) {
                Map<String, Object> result = new HashMap<>(1);
                result.put("tokenReqired", false);
                response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
                response.getWriter().write(JSON.toJSONString(result));
                return;
            }
            else if (!isAjaxRequest) {
                Cookie[] cookies = request.getCookies();
                if (null != cookies) {
                    for (Cookie cookie : cookies) {
                        if ("access_token".equals(cookie.getName())) {
                            accessToken = cookie.getValue();
                            break;
                        }
                    }
                }
            }
        }

        if (StringUtils.isEmpty(accessToken)) {
            chain.doFilter(request, response);
            return;
        }

        if (!checkTokenEnable(accessToken, request)) {
            if (isAjaxRequest && !WebUtils.ignoreToken(request.getRequestURI())) {
                Map<String, Object> result = new HashMap<>(1);
                result.put("tokenDisable", false);
                response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
                response.getWriter().write(JSON.toJSONString(result));
            }
            else {
                SecurityProperties securityProperties = SpringUtils.getBean(SecurityProperties.class);
                Cookie cookie = new Cookie("access_token", null);
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                redirectStrategy.sendRedirect(request, response,
                        String.format("/%s%s", securityProperties.getZuulProxyName(), request.getRequestURI()));
            }

            return;
        }

        if (!accessToken.startsWith("bearer ")) {
            accessToken = "bearer " + accessToken;
        }

        ModifyParametersWrapper requestWrapper = new ModifyParametersWrapper(request);
        requestWrapper.putHeader("Authorization", accessToken);
        chain.doFilter(requestWrapper, response);
        return;
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    private boolean checkTokenEnable(String accessToken, HttpServletRequest request) {
        try {
            int index = accessToken.indexOf(",");
            if (index > 0) {
                accessToken = accessToken.substring(0, index);
            }
            accessToken = accessToken.replace("bearer ", "");
            Claims body = Jwts.parser().setSigningKey("estone".getBytes("UTF-8")).parseClaimsJws(accessToken).getBody();
            String username = (String) body.get("user_name");
            TokenData tokenData = RedisUtils.get(username);
            if (tokenData == null) {
                return false;
            }

            String clientId = (String) body.get("client_id");
            String requestUserAgent = request.getHeader("user-agent");
            String userAgent = tokenData.getUserAgent();

            return accessToken.equals(tokenData.getAccessToken()) && ("browser".equals(clientId)
                    && (requestUserAgent.equals(userAgent) || requestUserAgent.contains("Java")));
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    private class ModifyParametersWrapper extends HttpServletRequestWrapper implements HttpServletRequest {
        private final Map<String, String> customHeaders;

        ModifyParametersWrapper(HttpServletRequest request) {
            super(request);
            this.customHeaders = new HashMap<>();
        }

        void putHeader(String name, String value) {
            this.customHeaders.put(name, value);
        }

        public String getHeader(String name) {
            String headerValue = customHeaders.get(name);
            if (headerValue != null) {
                return headerValue;
            }
            return ((HttpServletRequest) getRequest()).getHeader(name);
        }

        public Enumeration<String> getHeaders(String name) {
            Set<String> set = new HashSet<>();
            Enumeration<String> e = super.getHeaders(name);
            while (e.hasMoreElements()) {
                String n = e.nextElement();
                set.add(n);
            }
            Set<Entry<String, String>> entrySet = this.customHeaders.entrySet();
            for (Entry<String, String> entry : entrySet) {
                if (name.equals(entry.getKey())) {
                    set.add(entry.getValue());
                }
            }
            return Collections.enumeration(set);
        }

        public Enumeration<String> getHeaderNames() {
            Set<String> set = new HashSet<>(customHeaders.keySet());
            Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
            while (e.hasMoreElements()) {
                String n = e.nextElement();
                set.add(n);
            }
            return Collections.enumeration(set);
        }
    }
}
