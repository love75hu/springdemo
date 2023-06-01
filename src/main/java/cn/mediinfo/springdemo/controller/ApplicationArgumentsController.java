package cn.mediinfo.springdemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/v1/ApplicationArguments")
public class ApplicationArgumentsController {

    /**
     * 获取命令行参数
     * java -jar springdemo.jar --server.port=8081
     */
    @Autowired
    private ApplicationArguments args;
    /**
     * 获取memberProperties配置参数
     * @return
     */
    @Operation(summary = "获取获取命令行参数值")
    @GetMapping("get")
    public ApplicationArguments Get() {
        return args;
    }
}
