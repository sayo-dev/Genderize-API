package org.example.genderize.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(

        info = @Info(
                title = "Genderize API",
                description = "API Integration & Data Processing",
                version = "1.0"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "LOCAL ENV"
                )
        }
)
public class SwaggerConfig {
}
