package cn.mediinfo.springdemo.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class SwaggerConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "springdoc.openapi")
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI();
//        return new OpenAPI()
//                .info(new Info().title("SpringShop API")
//                        .description("Spring shop sample application")
//                        .version("v0.0.1")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
//                        .externalDocs(new ExternalDocumentation()
//                        .description("SpringShop Wiki Documentation")
//                        .url("https://springshop.wiki.github.org/docs"));
    }
}
