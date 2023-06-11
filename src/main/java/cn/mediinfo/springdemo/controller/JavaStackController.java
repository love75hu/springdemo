package cn.mediinfo.springdemo.controller;

import cn.mediinfo.springdemo.context.properties.JavaStackProperties;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/v1/javaStack")
@EnableConfigurationProperties(value = {JavaStackProperties.class})
@RequiredArgsConstructor
public class JavaStackController {

    private final JavaStackProperties javaStackProperties;


    /**
     * 获取javaStack配置参数
     * @return
     */
    @Operation(summary = "获取javaStack配置参数")
    @GetMapping("get")
    public JavaStackProperties Get() {
        return javaStackProperties;
    }
}
