package cn.mediinfo.springdemo.controller;

import cn.mediinfo.springdemo.mapper.ClientScopeMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping({"api/v1/ClientScopeMybatis"})
@RequiredArgsConstructor
public class ClientScopeMybatisController {
    private final ClientScopeMapper clientScopeMapper;

    /**
     * Mybatis获取数据demo
     *
     * @return
     * @throws Exception
     */
    @Operation(summary = "Mybatis获取数据demo")
    @GetMapping("get")
    public List<String> Get() {
        log.debug("Mybatis获取数据demo！！！！");
        return clientScopeMapper.Get();
    }
}
