package cn.mediinfo.springdemo.controller;

import cn.mediinfo.springdemo.context.datasource.DynamicDataSource;
import cn.mediinfo.springdemo.dto.clientscope.AddClientScopeDto;
import cn.mediinfo.springdemo.exception.CanShuException;
import cn.mediinfo.springdemo.model.ClientscopeEntity;
import cn.mediinfo.springdemo.response.MsfResponse;
import cn.mediinfo.springdemo.response.ResponseCodeEnum;
import cn.mediinfo.springdemo.service.ClientScopeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping({"api/v2/ClientScope", "api/v1/ClientScope"})
@Validated //参数约束注解
@CrossOrigin
@RequiredArgsConstructor
public class ClientScopeController {

    private final ClientScopeService ClientScopeService;

    /**
     * 获取手术通知信息
     *
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取手术通知信息")
    @GetMapping("get")
    public List<String> Get() {
        log.debug("测试！！！！");
        return ClientScopeService.Get();
    }

    /**
     * 根据ID获取客户端Scope信息
     *
     * @param Id
     * @return
     */
    @Operation(summary = "根据ID获取客户端Scope信息")
    @GetMapping("GetById")
    public List<ClientscopeEntity> GetById(@Parameter(description = "ID编号") String Id) throws CanShuException {
        if (Id == null || StringUtils.containsWhitespace(Id)) {
            // throw new RuntimeException("");
            throw new CanShuException("ID不能为空！");
        }

        return ClientScopeService.GetById(Id);
    }


    @Operation(summary = "翻页客户端Scope信息")
    @GetMapping("GetPageable")
    @DynamicDataSource
    public MsfResponse<List<ClientscopeEntity>> GetPageable(@Parameter(description = "页码", required = false) int Page, @Parameter(description = "每页显示数量", required = true) int Size) {
        if (Page < 0) {
            return MsfResponse.fail(ResponseCodeEnum.CANSHUYC);
        }

        return MsfResponse.success(ClientScopeService.GetPageable(Page, Size));
    }

    /**
     * 参数校验方式1
     *
     * @param ClientScope
     * @return
     */
    @Operation(summary = "新增ClientScope")
    @PostMapping("add1")
    public MsfResponse<AddClientScopeDto> Add(@Validated @RequestBody AddClientScopeDto ClientScope) {

        return MsfResponse.success(ClientScope);
    }

    /**
     * 参数校验方式2(注意：post方法，参数默认是以@RequestBody方式提交，可以定义指定的参数为@RequestParam通过参数提交)
     *
     * @param Id
     * @param name
     * @param scope
     * @param clientId
     * @return
     */
    @Operation(summary = "新增ClientScope")
    @PostMapping("/add2/{Id}")
    public MsfResponse Add(@Parameter(description = "ID编号") @PathVariable("Id") String Id,
                           @RequestParam(required = false) @Parameter(description = "姓名") String name,
                           @RequestParam @Parameter(description = "scope名称") @NotNull @Size(min = 1, max = 2) String scope,
                           @RequestParam @Parameter(description = "clientId") @NotNull @Size(min = 1, max = 64) String clientId) {


        return MsfResponse.success();
    }
}
