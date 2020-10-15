package cn.riris.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

@Configuration
@EnableOpenApi
@Profile("!prod")
@ConfigurationProperties(prefix = "swagger-info")
@Data
public class Swagger3Config {

    private String name;
    private String url;
    private String version;
    private String email;
    private String title;
    private String description;

    @Bean
    @ConditionalOnMissingBean(name = "docket")
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .paths(Predicate.not(PathSelectors.regex("/error*")))
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title)
                                   .description(description)
                                   .contact(new Contact(name, url, email))
                                   .version(version)
                                   .build();
    }

}
