package cn.mediinfo.springdemo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * java参数绑定示例
 */
@Data
@ConfigurationProperties(prefix = "javastack")
public class JavaStackProperties {
    private String name;
    private List<String> user;
    private Map<String, String> params;
    private Security security;
}

@Data
 class Security {
    private String key;
    private String code;
}
