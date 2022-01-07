package evrentan.examples.springbootprojectexample.spring.spring;

import evrentan.examples.springbootprojectexample.spring.config.CommonConfig;
import evrentan.examples.springbootprojectexample.spring.config.SwaggerConfig;
import evrentan.examples.springbootprojectexample.spring.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {CommonConfig.class, WebConfig.class, SwaggerConfig.class})
public class SpringBootProjectExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootProjectExampleApplication.class, args);
  }

}
