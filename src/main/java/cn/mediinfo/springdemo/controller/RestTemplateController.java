package cn.mediinfo.springdemo.controller;

import cn.mediinfo.springdemo.response.MsfResponse;
import cn.mediinfo.springdemo.response.ResponseCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping({"api/v1/RestTemplate"})
public class RestTemplateController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取一个远程页面
     * @return
     */
    @Operation(summary = "获取kelink.com页面内容")
    @GetMapping
    public MsfResponse<String> Get(String Path) {
        // 发送 GET 请求
        String url = "http://kelink.com";
        var response = restTemplate.getForEntity(url,String.class);

        // 解析响应结果
        if (response.getStatusCodeValue() == HttpStatus.OK.value()) {
            return MsfResponse.success(response.getBody());
        } else {
            return  MsfResponse.fail(ResponseCodeEnum.CHAOSHIYC ,String.format("获取用户信息失败，错误码：%d", response.getStatusCodeValue()));
        }
    }
}
