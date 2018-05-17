package com.estone.erp.common.security.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.estone.erp.common.security.properties.SecurityProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * 表单登录controller, 跳转到登录页面
 */
@Slf4j
@Controller
public class LoginController {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Autowired
    private SecurityProperties securityProperties;

    @GetMapping("/authentication/require")
    public String loginAuth(HttpServletRequest request, HttpServletResponse response) {
        if ("usermgt".equals(securityProperties.getZuulProxyName())) {
            return "login";
        }
        try {
            SavedRequest savedRequest = requestCache.getRequest(request, response);
            if (savedRequest != null) {
                String redirectUrl = savedRequest.getRedirectUrl();
                request.getServletContext().getContextPath();
                request.getRequestURI();
                if (StringUtils.isNotEmpty(redirectUrl)) {
                    int indexOf = redirectUrl.indexOf("/", 8);
                    StringBuffer sb = new StringBuffer(redirectUrl);
                    String zuulProxyName = "/%s";
                    redirectUrl = sb
                            .insert(indexOf, String.format(zuulProxyName, securityProperties.getZuulProxyName()))
                            .toString();
                }
                Cookie cookie = new Cookie("redirectUrl", redirectUrl);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            redirectStrategy.sendRedirect(request, response, "/usermgt/authentication/require");
        }
        catch (IOException e) {
            log.warn("----------------> 表单登录重定向异常  <----------------");
            throw new RuntimeException("表单登录重定向异常 ");
        }

        return null;
    }
}
