package com.estone.erp.usermgt.security.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.estone.erp.common.model.TokenData;
import com.estone.erp.common.util.RedisUtils;
import com.estone.erp.common.util.WebUtils;

/**
 * 认证成功的处理类
 */
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    @Qualifier("defaultAuthorizationServerTokenServices")
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.warn("登录成功");
        String redirectUrl = "/web/wish/page";
        if (WebUtils.isAjaxRequest(request)) {
            logger.warn("AJAX请求..");

            // 生成token
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId("browser");
            TokenRequest tokenRequest = new TokenRequest(null, "browser", clientDetails.getScope(), "bearer");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices
                    .createAccessToken(oAuth2Authentication);
            String token = oAuth2AccessToken.getValue();

            // 存储token加上ip和浏览器信息
            TokenData tokenData = new TokenData();
            tokenData.setAccessToken(token);
            tokenData.setIp(request.getRemoteHost());
            tokenData.setUserAgent(request.getHeader("user-agent"));
            RedisUtils.set(authentication.getName(), tokenData);

            Cookie cookie = new Cookie("access_token", token);
            cookie.setPath("/");
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
            Map<String, Object> result = new HashMap<>();
            result.put("accessToken", token);
            result.put("redirectUrl", redirectUrl);
            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            response.setCharacterEncoding(Consts.UTF_8.name());
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }
        else {
            logger.warn("非AJAX请求..");
            // 待处理
            redirectStrategy.sendRedirect(request, response, "/web/wish/page");
        }
    }
}
