package com.devweb.modelvirtualbe.shared.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI customOpenApi(
            @Value("${documentation.application.description}") String applicationDescription,
            @Value("${documentation.application.version}") String applicationVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Model Virtual API")
                        .version(applicationVersion)
                        .description(applicationDescription)
                        .termsOfService("https://devweb-modelvirtual.com/tos")
                        .license(new License().name("Apache 2.0 License").url("https://devweb-modelvirtual.com/license"))
                        .contact(new Contact()
                                .url("https://devweb.studio")
                                .name("DEVWEB.studio")));
    }
}
