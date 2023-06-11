package cn.mediinfo.springdemo.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfigration {
    /**
     * 创建一个 RestTemplate 实
     * 需要快速简单地实现 RESTful API 调用，建议使用 RestTemplate；
     * 如果需要实现微服务之间的远程调用，并且希望以面向接口编程的方式来调用 RESTful 服务，可以考虑使用 OpenFeign
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
