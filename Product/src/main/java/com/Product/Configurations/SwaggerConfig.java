package com.Product.Configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    GroupedOpenApi openAPI() {
        return GroupedOpenApi.builder().group("Products").pathsToMatch("/**").build();
    }

    @Bean
    OpenAPI customApi() {
        return new OpenAPI().info(new Info().title("Product Service").version("v1"));
    }

}
