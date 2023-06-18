package cn.mediinfo.springdemo.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;

@Component //注册到spring中
@WebEndpoint(id = "test") //暴露为http访问的端点
public class EndPointController {

    /**
     * 获取端点
     * see link get https://localhost:8000/mediinfo-springdemo/actuator2/test/1
     *
     * @param id
     * @return
     */
    @ReadOperation
    public String get(@Selector String id) {
        return id;
    }

    /**
     * 获取端点
     * see link post https://localhost:8000/mediinfo-springdemo/actuator2/test
     *
     * @param id
     * @return
     */
    @WriteOperation
    public String post(@NotNull String id) {
        return id;
    }
}
