package com.example.User.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    GroupedOpenApi openAPI() {
        return GroupedOpenApi.builder().group("userService").pathsToMatch("/**").build();
    }

    @Bean
    OpenAPI customApi() {
        return new OpenAPI().info(new Info().title("User Service").version("v1"))
                .addSecurityItem(new SecurityRequirement().addList("Auth-token"))
                .components(
                        new Components().addSecuritySchemes("Auth-token", new SecurityScheme().type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER).name("Auth-token"))
                );
    }

}
