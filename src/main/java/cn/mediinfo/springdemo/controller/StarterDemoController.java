package cn.mediinfo.springdemo.controller;


import cn.mediinfo.javastackspringbootstarter.service.TestService;
import cn.mediinfo.springdemo.response.MsfResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("api/v1/StarterDemo")
//@Import(TestService.class)
public class StarterDemoController {

    @Autowired
    private TestService testService;

    /**
     * 获取StarterDemo配置参数
     * @return
     */
    @Operation(summary = "获取StarterDemo配置参数")
    @GetMapping("get")
    public MsfResponse Get() {
        return MsfResponse.success(testService.sayHello()) ;
    }
}
