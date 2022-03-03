package com.moskalev.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vasiliy Moskalev
 * @version 1.1
 * @since 09.02.22
 * Class configuration for Swagger
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Logic company",
        version = "1.0.0",
        description = "Logic company"))
public class SwaggerConfiguration {
}
