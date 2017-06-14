package io.swagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerDocumentationConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Cloudiator REST Api")
            .description("No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)")
            .license("Apache License 2.0")
            .licenseUrl("https://raw.githubusercontent.com/cloudiator/rest/master/LICENSE")
            .termsOfServiceUrl("http://cloudiator.org")
            .version("0.2.0")
            .contact(new Contact("","", "daniel.baur@uni-ulm.de"))
            .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("io.github.cloudiator.rest.api"))
                    .build()
                .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

}