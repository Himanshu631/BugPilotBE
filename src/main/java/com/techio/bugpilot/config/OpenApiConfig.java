package com.techio.bugpilot.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BugPilot Backend")
                        .description("API documentation for BugPilot")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Himanshu Paul")
                                .email("himanshupauljsr@gmail.com")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Project GitHub")
                        .url("https://github.com/your-repo"));
    }
}

