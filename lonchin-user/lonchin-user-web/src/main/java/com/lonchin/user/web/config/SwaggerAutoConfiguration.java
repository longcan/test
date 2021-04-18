package com.lonchin.user.web.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;

/**
 * Swagger文档生成配置
 *
 * @author dylan
 */
@Slf4j
@Configuration
@EnableKnife4j
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerAutoConfiguration {

    @Bean
    public Docket createAll() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).groupName("全部")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.craftsman.ddl"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class)
                .directModelSubstitute(ZonedDateTime.class, String.class)
                .securitySchemes(Arrays.asList(securitySchemeToken(), securitySchemeClientType()));
    }

    @Bean
    SecurityScheme securitySchemeToken() {
        return new ApiKey("BearerToken", "Authorization", "header");
    }

    @Bean
    SecurityScheme securitySchemeClientType() {
        return new ApiKey("Company", "clientX", "header");
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "当当龙老师中心 API文档",
                "接口和类定义",
                "0.0.1-SNAPSHOT",
                "https://my.rdc.aliyun.com",
                new Contact("Dylan", "https://my.rdc.aliyun.com", "0095103cn@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
