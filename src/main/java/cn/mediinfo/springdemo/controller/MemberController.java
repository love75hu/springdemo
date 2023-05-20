package cn.mediinfo.springdemo.controller;

import cn.mediinfo.springdemo.config.properties.JavaStackProperties;
import cn.mediinfo.springdemo.config.properties.MemberProperties;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/v1/Member")
//@EnableConfigurationProperties(value = {MemberProperties.class})
public class MemberController {


    @Autowired
    private MemberProperties memberProperties;

    /**
     * 获取memberProperties配置参数
     * @return
     */
    @Operation(summary = "获取member构造器绑定配置参数")
    @GetMapping("get")
    public MemberProperties Get() {
        return memberProperties;
    }
}
