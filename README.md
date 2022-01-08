# A Complete Spring Boot Example Project
A Complete Spring Boot Example Project with Spring Boot 2.5.6, JDK 17 & Maven.

## Table of Contents

1. [How to Contribute](#how-to-contribute)
2. [Requirements](#requirements)
3. [Running the Application Locally](#running-the-application-locally)
4. [Run Actuator](#run-actuator)
5. [Run Swagger UI Documentation](#run-swagger-ui-documentation)
6. [Copyright](#copyright)

## How to Contribute

For the contributor covenant to this project, please check the Code of Conduct file.

[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](https://github.com/evrentan/spring-boot-project-example/blob/46d765858d1e4dce96810e1749599a11dc604fe4/CODE_OF_CONDUCT.md)

## Requirements

For building and running the application belows are required;

- [Spring Boot 2.5.6](https://spring.io/blog/2021/10/21/spring-boot-2-5-6-is-now-available)
- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.8.3](https://maven.apache.org)
- Springfox Boot Starter 3.0.0 for Swagger UI Documentation
- MongoDB

## Running the application locally

The project can be booted with Spring Cloud Config Server or directly within the application. In order to boot the project within itself, enable the properties in application.properties file and disable bootstrap.properties file.

Application can be run with SpringBootProjectExampleApplication class under evrentan.examples.springbootprojectexample.spring.config.spring package.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

##Run Actuator

[Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/) can be reached from [local url for Actuator](http://localhost:8081/actuator).

Only health and caches endpoints are enabled by default. Configuration can be updated within the "actuator" section of the related application.properties file. This file can be also in Spring Cloud Config Server if the application is booted with Spring Cloud Config Server.

##Run Swagger UI Documentation
After running the application, just type the  [local url for Swagger UI](http://localhost:8080/swagger-ui/index.html) in your browser.

## Copyright

GNU General Public License v3.0
Permissions of this strong copyleft license are conditioned on making available complete source code of licensed works and modifications, which include larger works using a licensed work, under the same license. Copyright and license notices must be preserved. Contributors provide an express grant of patent rights.
