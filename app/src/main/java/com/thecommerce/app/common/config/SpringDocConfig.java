package com.thecommerce.app.common.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("더 커머스 백엔드 기술 과제 API 문서")
                        .description("더 커머스 백엔드 개발자 기술 과제 API 문서 입니다.")
                        .version("v1.0.0"));
    }
}
