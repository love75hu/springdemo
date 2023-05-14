package cn.mediinfo.springdemo.controller;

import cn.mediinfo.springdemo.service.ClientScopeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Target;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/ClientScope")
public class testController {

    @Autowired
    private ClientScopeService  ClientScopeService;

    /**
     * 获取手术通知信息
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取手术通知信息")
    @GetMapping("get")
    public List<String> Get()
    {
        log.debug("测试！！！！");
        return  ClientScopeService.Get();
    }
}
