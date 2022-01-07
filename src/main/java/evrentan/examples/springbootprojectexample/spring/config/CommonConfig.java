package evrentan.examples.springbootprojectexample.spring.config;

import evrentan.examples.springbootprojectexample.mapper.CustomObjectMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "evrentan.examples.springbootprojectexample")
@EnableMongoRepositories(value = "evrentan.examples.springbootprojectexample.repository")
@EntityScan(value = "evrentan.examples.springbootprojectexample.entity")
@EnableWebMvc
public class CommonConfig {

  @Bean(name = "customObjectMapper")
  public CustomObjectMapper customObjectMapper() {
    return new CustomObjectMapper();
  }
}
