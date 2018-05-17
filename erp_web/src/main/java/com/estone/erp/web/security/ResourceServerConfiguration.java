package com.estone.erp.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import com.estone.erp.common.security.config.ResourceServerConfig;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfig {

    @Override
    public void configureHttp(
            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests)
            throws Exception {
    }
}