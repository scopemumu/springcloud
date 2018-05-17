package com.estone.erp.common.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties
@Component
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityProperties {
    private String zuulProxyName;
}
