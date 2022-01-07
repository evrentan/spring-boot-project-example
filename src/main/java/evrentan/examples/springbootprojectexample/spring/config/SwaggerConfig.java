package evrentan.examples.springbootprojectexample.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurationSupport {

  ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("My Swagger UI Example")
        .version("1.0.0")
        .contact(new Contact(
            "Evren Tan",
            "https://evrentan.com",
            "info@evrentan.com"))
        .build();
  }

  @Bean
  public Docket allApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("All APIs")
        .select()
        .apis(RequestHandlerSelectors.basePackage("evrentan.examples.springbootprojectexample.controller"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
  }

  @Bean
  public Docket customerApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("Customer APIs")
        .select()
        .apis(RequestHandlerSelectors.basePackage("evrentan.examples.springbootprojectexample.controller"))
        .paths(PathSelectors.regex("/customer.*"))
        .build()
        .apiInfo(apiInfo());
  }

  @Bean
  public Docket customerRefApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("Customer Ref APIs")
        .select()
        .apis(RequestHandlerSelectors.basePackage("evrentan.examples.springbootprojectexample.controller"))
        .paths(PathSelectors.regex("/refCustomer.*"))
        .build()
        .apiInfo(apiInfo());
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
}
