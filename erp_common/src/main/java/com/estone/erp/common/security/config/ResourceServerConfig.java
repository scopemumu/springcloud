package com.estone.erp.common.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.estone.erp.common.config.ERPCommonConfig;
import com.estone.erp.common.security.filter.ApiTokenAccessFilter;
import com.estone.erp.common.security.filter.PreFilter;
import com.estone.erp.common.security.properties.SecurityProperties;

/**
 * 资源安全配置抽象类, 各个子模块实现抽象方法即可
 */
public abstract class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("jwtTokenStore")
    public TokenStore jwtTokenStore;

    @Autowired
    @Qualifier("jwtAccessTokenConverter")
    public JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    @Qualifier("resourceServerTokenServices")
    public ResourceServerTokenServices resourceServerTokenServices;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ERPCommonConfig erpCommonConfig;

    /**
     * 对资源的一个总体配置, 默认对所有资源进行数据权限查询
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        String loginPage = "/%s/authentication/require";
        http.csrf().disable();
        http.formLogin().loginPage(String.format(loginPage, securityProperties.getZuulProxyName())); // 未认证跳转的登录页面
        http.addFilterBefore(new ApiTokenAccessFilter(resourceServerTokenServices),
                AbstractPreAuthenticatedProcessingFilter.class);
        http.addFilterBefore(new PreFilter(), ApiTokenAccessFilter.class);

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http
                .authorizeRequests();
        authorizeRequests.antMatchers(erpCommonConfig.getIgnoreTokenUrls()).permitAll();
        configureHttp(authorizeRequests);
        authorizeRequests.anyRequest().access("@iRbacService.hasPermission(request, authentication)");
    }

    public abstract void configureHttp(
            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests)
            throws Exception;

}