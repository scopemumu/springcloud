package com.estone.erp.web.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.estone.erp.web.component.WebExceptionResolver;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new WebExceptionResolver());
    }
}
