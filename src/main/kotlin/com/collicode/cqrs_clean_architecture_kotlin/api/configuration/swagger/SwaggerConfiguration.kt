package com.collicode.cqrs_clean_architecture_kotlin.api.configuration.swagger

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Kotlin Spring Clean Architecture Microservice")
                    .description(
                        """
                            Clean Architecture Microservice
                        """.trimIndent(),
                    )
                    .contact(
                        Contact()
                            .name("Collins Kiplimo")
                            .email("limokcollins@gmail.com")
                            .url("https://github.com/c-kiplimo")
                    )
                    .version("1.0.0")
            )
            .addServersItem(Server().url("http://localhost:8080/").description("dev"))
    }
}