package cn.mediinfo.springdemo.controller;

import cn.mediinfo.springdemo.enums.AbstractMethod;
import cn.mediinfo.springdemo.enums.BasicOperation;
import cn.mediinfo.springdemo.enums.Direction;
import cn.mediinfo.springdemo.response.MsfResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "EnumController", description = "枚举高级用法")
@RestController
@Slf4j
@RequestMapping("api/v1/Enum")
public class EnumController {
    @Operation(summary = "获取带字段和方法的枚举")
    @GetMapping("getDirection")
    public MsfResponse<Object> GetDirection() {
        return MsfResponse.success(Direction.NORTH);
    }

    @Operation(summary = "获取实现接口的枚举")
    @GetMapping("getBasicOperation")
    public MsfResponse<Object> GetBasicOperation() {
        return MsfResponse.success(BasicOperation.ADDITION.apply(1,2));
    }

    @Operation(summary = "获取实现虚方法的枚举")
    @GetMapping("getAbstractMethod")
    public MsfResponse<Object> GetAbstractMethod() {
        return MsfResponse.success(AbstractMethod.google.getBuilder("测试一下"));
    }
}
