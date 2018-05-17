package com.estone.erp.usermgt.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.estone.erp.common.config.ERPCommonConfig;
import com.estone.erp.common.security.config.ResourceServerConfig;
import com.estone.erp.common.security.filter.ApiTokenAccessFilter;
import com.estone.erp.common.security.filter.PreFilter;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfig {

    @Autowired
    private SavedRequestAwareAuthenticationSuccessHandler awareAuthenticationSuccessHandler;

    @Autowired
    private SimpleUrlAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ERPCommonConfig erpCommonConfig;

    @Override
    public void configureHttp(
            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests)
            throws Exception {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin().loginPage("/authentication/require") // 未认证跳转的登录页面
                .loginProcessingUrl("/authentication/form") // 表单请求路径
                .successHandler(awareAuthenticationSuccessHandler).failureHandler(authenticationFailureHandler);
        http.addFilterBefore(new ApiTokenAccessFilter(resourceServerTokenServices),
                AbstractPreAuthenticatedProcessingFilter.class);
        http.addFilterBefore(new PreFilter(), ApiTokenAccessFilter.class);
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http
                .authorizeRequests();
        authorizeRequests.antMatchers(erpCommonConfig.getIgnoreTokenUrls()).permitAll();
        configureHttp(authorizeRequests);
        authorizeRequests.anyRequest().access("@iRbacService.hasPermission(request, authentication)");
    }
}