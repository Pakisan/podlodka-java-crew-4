package com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.rest;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customOpenAPI(@Value("${springwolf.docket.info.version}") String version,
                                 @Value("${spring.application.name}") String name,
                                 @Value("${springwolf.docket.info.description}") String description,
                                 @Value("${springwolf.docket.info.terms-of-service}") String termsOfService
    ) {
        return new OpenAPI()
                .info(new Info()
                        .title(name)
                        .version(version)
                        .description(description)
                        .termsOfService(termsOfService)
                );
    }

}
