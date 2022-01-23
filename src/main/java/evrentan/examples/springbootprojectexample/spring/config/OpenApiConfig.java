package evrentan.examples.springbootprojectexample.spring.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration Class for Open API Configuration used for API Documentation with SpringDoc OpenApi Library.
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>, <a href="https://github.com/erkanerkisi">Erkan Erkisi</a>
 * @since 1.0.0
 */
@Configuration
public class OpenApiConfig {

    /**
     * Controller Path static value in String type.
     */
    private static final String DEFAULT_CONTROLLER_PATH = "evrentan.examples.springbootprojectexample.controller";


    /**
     * Bean declaration for Open API documentation details.
     *
     * @return OpenAPI. Please, see the {@link io.swagger.v3.oas.models.OpenAPI} class for details.
     *
     * @author <a href="https://github.com/evrentan">Evren Tan</a>, <a href="https://github.com/erkanerkisi">Erkan Erkisi</a>
     * @since 1.0.0
     */
    @Bean
    public OpenAPI openApiDocumentation() {
        Contact contact = new Contact();
        contact.setName("Evren Tan");
        contact.setEmail("info@evrentan.com");
        contact.setUrl("https://evrentan.com");
        return new OpenAPI()
            .info(new Info().title("Swagger UI")
                .description("Spring Boot Project Example Swagger UI")
                .version("1.0.0")
                .contact(contact)
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
            .externalDocs(new ExternalDocumentation()
                .description("Spring Boot Project Example Swagger UI"));
    }

    /**
     * Bean declaration for Open API documentation details for all APIs within the {@link evrentan.examples.springbootprojectexample.spring.config.OpenApiConfig#DEFAULT_CONTROLLER_PATH} field.
     *
     * @return OpenAPI. Please, see the {@link org.springdoc.core.GroupedOpenApi} class for details.
     *
     * @author <a href="https://github.com/evrentan">Evren Tan</a>, <a href="https://github.com/erkanerkisi">Erkan Erkisi</a>
     * @since 1.0.0
     */
    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("all-api")
                .packagesToScan(DEFAULT_CONTROLLER_PATH)
                .build();
    }

    /**
     * Bean declaration for Open API documentation details for all APIs within the {@link evrentan.examples.springbootprojectexample.spring.config.OpenApiConfig#DEFAULT_CONTROLLER_PATH} field and matching "/customer/**" end-points.
     *
     * @return OpenAPI. Please, see the {@link org.springdoc.core.GroupedOpenApi} class for details.
     *
     * @author <a href="https://github.com/evrentan">Evren Tan</a>, <a href="https://github.com/erkanerkisi">Erkan Erkisi</a>
     * @since 1.0.0
     */
    @Bean
    public GroupedOpenApi customerApi() {
        return GroupedOpenApi.builder()
                .group("customer-api")
                .packagesToScan(DEFAULT_CONTROLLER_PATH)
                .pathsToMatch("/customer/**")
                .build();
    }

    /**
     * Bean declaration for Open API documentation details for all APIs within the {@link evrentan.examples.springbootprojectexample.spring.config.OpenApiConfig#DEFAULT_CONTROLLER_PATH} field and matching "/refCustomer/**" end-points.
     *
     * @return OpenAPI. Please, see the {@link org.springdoc.core.GroupedOpenApi} class for details.
     *
     * @author <a href="https://github.com/evrentan">Evren Tan</a>, <a href="https://github.com/erkanerkisi">Erkan Erkisi</a>
     * @since 1.0.0
     */
    @Bean
    public GroupedOpenApi customerRefApi() {
        return GroupedOpenApi.builder()
                .group("customer-ref-api")
                .packagesToScan(DEFAULT_CONTROLLER_PATH)
                .pathsToMatch("/refCustomer/**")
                .build();
    }
}
