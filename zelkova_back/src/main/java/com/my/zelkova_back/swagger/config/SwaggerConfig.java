package com.my.zelkova_back.swagger.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


public class SwaggerConfig {

	@Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Zelkova API Docs")
                .version("v1")
                .description("Zelkova 백엔드 API 명세서"));
    }
}
