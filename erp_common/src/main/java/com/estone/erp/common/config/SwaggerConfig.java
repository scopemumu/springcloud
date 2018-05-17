package com.estone.erp.common.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/***
 * swaggger配置文件， 依赖项目在application.yml中配置
 * 
 * @author Kevin
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter implements EnvironmentAware {
    /**
     * 服务名
     */
    private String serviceName;

    /**
     * 联系人
     */
    private String contactName;

    /**
     * 联系人url
     */
    private String contactUrl;

    /**
     * 联系人email
     */
    private String contactEmail;

    /**
     * 服务描述
     */
    private String description;

    /**
     * 属性Resolver
     */
    private RelaxedPropertyResolver propertyResolver;

    public SwaggerConfig() {
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[] { "swagger-ui.html" })
                .addResourceLocations(new String[] { "classpath:/META-INF/resources/" });
        registry.addResourceHandler(new String[] { "/webjars*" })
                .addResourceLocations(new String[] { "classpath:/META-INF/resources/webjars/" });
    }

    @Bean
    public Docket createRestApi() {
        return (new Docket(DocumentationType.SWAGGER_2)).apiInfo(this.apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.estone.erp")).paths(PathSelectors.any()).build()
                .pathMapping("/").globalOperationParameters(setHeaderToken());
    }

    private List<Parameter> setHeaderToken() {
        ParameterBuilder paramBuilder = new ParameterBuilder();
        List<Parameter> params = new ArrayList<>(1);
        paramBuilder.name("authorization").description("token").modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        params.add(paramBuilder.build());
        return params;
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(contactName, contactUrl, contactEmail);
        return new ApiInfoBuilder().title(this.serviceName + " Restful APIs").description(this.description)
                .contact(contact).version("1.0").build();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, (String) null);
        this.serviceName = this.propertyResolver.getProperty("swagger.service.name");
        this.description = this.propertyResolver.getProperty("swagger.service.description");
        this.contactName = this.propertyResolver.getProperty("swagger.contact.name");
        this.contactUrl = this.propertyResolver.getProperty("swagger.contact.url");
        this.contactEmail = this.propertyResolver.getProperty("swagger.contact.email");
    }
}
