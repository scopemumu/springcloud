package com.estone.erp.common.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.estone.erp.common.security.exceptionhandler.ExpiredTokenExceptionHandler;

public class ApiTokenAccessFilter extends OAuth2AuthenticationProcessingFilter {

    public ApiTokenAccessFilter(ResourceServerTokenServices resourceServerTokenServices) {
        super();
        setStateless(false);
        setAuthenticationManager(oauthAuthenticationManager(resourceServerTokenServices));
        setAuthenticationEntryPoint(new ExpiredTokenExceptionHandler());
    }

    private AuthenticationManager oauthAuthenticationManager(ResourceServerTokenServices tokenServices) {
        OAuth2AuthenticationManager oauthAuthenticationManager = new OAuth2AuthenticationManager();
        oauthAuthenticationManager.setResourceId("oauth2-resource");
        oauthAuthenticationManager.setTokenServices(tokenServices);
        oauthAuthenticationManager.setClientDetailsService(null);

        return oauthAuthenticationManager;
    }
}