package evrentan.examples.springbootprojectexample.spring.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    private static final String DEFAULT_CONTROLLER_PATH = "evrentan.examples.springbootprojectexample.controller";

    @Bean
    public OpenAPI springShopOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Evren Tan");
        contact.setEmail("info@evrentan.co");
        contact.setUrl("https://evrentan.com");
        return new OpenAPI()
                .info(new Info().title("My Swagger UI Example")
                        .description("Example Project")
                        .version("1.0.0")
                        .contact(contact)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Example Documentation"));
    }

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("all-api")
                .packagesToScan(DEFAULT_CONTROLLER_PATH)
                .build();
    }

    @Bean
    public GroupedOpenApi customerApi() {
        return GroupedOpenApi.builder()
                .group("customer-api")
                .packagesToScan(DEFAULT_CONTROLLER_PATH)
                .pathsToMatch("/customer/**")
                .build();
    }

    @Bean
    public GroupedOpenApi customerRefApi() {
        return GroupedOpenApi.builder()
                .group("customer-ref-api")
                .packagesToScan(DEFAULT_CONTROLLER_PATH)
                .pathsToMatch("/refCustomer/**")
                .build();
    }
}
