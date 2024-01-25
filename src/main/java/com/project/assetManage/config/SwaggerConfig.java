package com.project.assetManage.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
        Info info = new Info()
                .title("CMS platform API docs")
                .version(springdocVersion)
                .description("cms프로젝트에 쓰이는 api 정리");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
