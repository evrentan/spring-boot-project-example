package evrentan.examples.springbootprojectexample.spring.config;

import evrentan.examples.springbootprojectexample.mapper.CustomObjectMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Configuration Class for Common Configurations.
 * This configuration class includes component, repository, entity scan & it enables Web MVC.
 * Also, this configuration class declares customObjectMapper bean.
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
@Configuration
@ComponentScan(basePackages = "evrentan.examples.springbootprojectexample")
@EnableMongoRepositories(value = "evrentan.examples.springbootprojectexample.repository")
@EntityScan(value = "evrentan.examples.springbootprojectexample.entity")
@EnableWebMvc
public class CommonConfig {

  /**
   * Bean declarations for customerObjectMapper.
   *
   * @return CustomObjectMapper. Please, see the {@link evrentan.examples.springbootprojectexample.mapper.CustomObjectMapper} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @Bean(name = "customObjectMapper")
  public CustomObjectMapper customObjectMapper() {
    return new CustomObjectMapper();
  }
}
