package cn.mediinfo.springdemo.context.annotation.Configuration;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 * 图形验证码配置
 */
@SpringBootConfiguration
public class KapchaConfiguration {

    @Bean
    public Producer kaptcha() {
        Properties properties = new Properties();
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "150");//验证码宽度
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "150");//验证码高度
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789");//验证码内容字符集
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");//验证码生成长度

        var config = new Config(properties);

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
