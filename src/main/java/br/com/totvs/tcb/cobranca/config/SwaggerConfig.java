package br.com.totvs.tcb.cobranca.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile({"dev", "test"})
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final Messages messages;

    @Value("${app.build.version}")
    private String appBuildVersion;

    public SwaggerConfig(Messages messages) {
        this.messages = messages;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("br.com.totvs.tcb.cobranca.controller"))
                .paths(PathSelectors.any()).build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(messages.get("swagger.title"))
                .description(messages.get("swagger.description"))
                .version(appBuildVersion)
                .build();
    }

}
