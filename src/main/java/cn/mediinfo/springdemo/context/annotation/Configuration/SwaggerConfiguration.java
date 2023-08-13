package cn.mediinfo.springdemo.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class SwaggerConfiguration {

    //获取配置的属性值
    @Value("${java.starter.message}")
    private String message;

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
