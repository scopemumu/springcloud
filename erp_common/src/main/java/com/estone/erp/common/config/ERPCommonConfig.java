package com.estone.erp.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class ERPCommonConfig {
    @Value("${erp.common.ignoreTokenUrls}")
    private String[] ignoreTokenUrls;
}
