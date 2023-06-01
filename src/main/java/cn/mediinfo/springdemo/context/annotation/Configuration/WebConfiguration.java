package cn.mediinfo.springdemo.context.annotation.Configuration;

import cn.mediinfo.springdemo.context.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义web配置类
 */
@Configuration
/**
 * RequiredArgsConstructor是一种Lombok注解，它用于自动生成一个包含所有必需参数的构造函数，这些参数可以是通过类的字段定义或者final修饰的方法参数来实现的。
 */
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;

    /**
     * 添加拦截器
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/v1/Interceptor/**");//对该路径进行过滤
    }
}
