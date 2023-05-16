package cn.mediinfo.springdemo.controller;

import cn.mediinfo.springdemo.model.ClientscopeEntity;
import cn.mediinfo.springdemo.response.MsfResponse;
import cn.mediinfo.springdemo.response.ResponseCodeEnum;
import cn.mediinfo.springdemo.service.ClientScopeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/ClientScope")
public class ClientScopeController {

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

    /**
     * 根据ID获取客户端Scope信息
     * @param Id
     * @return
     */
    @Operation(summary = "根据ID获取客户端Scope信息")
    @GetMapping("GetById")
    public List<ClientscopeEntity> GetById(@Parameter(description = "ID编号") String Id)
    {
        if (Id==null||StringUtils.isWhitespace(Id))
        {
           throw new RuntimeException("ID不能为空！");
        }

        return  ClientScopeService.GetById(Id);
    }


    @Operation(summary = "翻页客户端Scope信息")
    @GetMapping("GetPageable")
    public MsfResponse<List<ClientscopeEntity>> GetPageable(@Parameter(description = "页码") int Page, @Parameter(description = "每页显示数量") int Size)
    {
        if (Page<0)
        {
            return MsfResponse.fail(ResponseCodeEnum.CANSHUYC);
        }

        return MsfResponse.success(ClientScopeService.GetPageable(Page,Size));
    }
}
