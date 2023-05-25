package cn.mediinfo.springdemo.controller;

import cn.mediinfo.springdemo.config.properties.MemberProperties;
import cn.mediinfo.springdemo.response.MsfResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * 导入配置
 */
@RestController
@Slf4j
@RequestMapping("api/v1/Import")
public class ImportController {
    @Autowired
    private OpenAPI openAPI;
    /**
     * 获取非@ComponentScan扫描范围的bean OpenAPI配置参数
     * @return
     */
    @Operation(summary = "获取OpenAPI配置参数")
    @GetMapping("get")
    public MsfResponse<Object> Get() {
        return MsfResponse.success(openAPI) ;
    }
}
