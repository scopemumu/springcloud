package com.estone.erp.common.security.exceptionhandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import com.estone.erp.common.security.properties.SecurityProperties;
import com.estone.erp.common.util.SpringUtils;

public class ExpiredTokenExceptionHandler implements AuthenticationEntryPoint {
    private static Logger logger = Logger.getLogger(ExpiredTokenExceptionHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        SecurityProperties securityProperties = SpringUtils.getBean(SecurityProperties.class);
        try {
                String redirectUrl = request.getRequestURL().toString();
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
                Cookie tokenCookie = new Cookie("access_token", null);
                tokenCookie.setPath("/");
                tokenCookie.setMaxAge(0);
                response.addCookie(tokenCookie);
            redirectStrategy.sendRedirect(request, response, "/usermgt/authentication/require");
        }
        catch (IOException e) {
            logger.warn("----------------> token验证失败后表单登录重定向异常  <----------------");
            throw new RuntimeException("token验证失败后表单登录重定向异常 ");
        }
    }

}
