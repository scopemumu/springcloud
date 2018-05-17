package com.estone.erp.usermgt.security.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.estone.erp.common.util.WebUtils;

/**
 * 认证失败的处理类
 */
@Component
public class AuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");
        if (WebUtils.isAjaxRequest(request)) {
            Map<String, Object> map = new HashMap<>();
            map.put("authorizedFail", true);
            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            response.setCharacterEncoding(Consts.UTF_8.name());
            response.getWriter().write(JSON.toJSONString(map));
        }
        else {
            // 待处理
            super.onAuthenticationFailure(request, response, exception);
        }
    }

}
