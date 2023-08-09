package cn.mediinfo.springdemo.context.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * java参数绑定示例
 */
@Data
//@Profile("dev") //表示只有在dev环境才会被激活
@ConfigurationProperties(prefix = "javastack")
public class JavaStackProperties {
    //@NotNull
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
