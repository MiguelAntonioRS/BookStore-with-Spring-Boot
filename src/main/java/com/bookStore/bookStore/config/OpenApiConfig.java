package com.bookStore.bookStore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BookStore API")
                        .version("1.0.0")
                        .description("REST API for BookStore Management System. " +
                                "This API allows developers to integrate book management capabilities " +
                                "into their own applications.")
                        .contact(new Contact()
                                .name("Miguel Antonio")
                                .email("miguel@example.com")
                                .url("https://github.com/MiguelAntonioRS"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Development Server")
                ));
    }
}