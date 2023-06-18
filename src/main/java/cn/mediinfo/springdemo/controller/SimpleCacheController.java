package cn.mediinfo.springdemo.controller;

import cn.mediinfo.springdemo.response.MsfResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用spring boot 缓存，该缓存可以指定具体的缓存依赖（memory、redis等），主要是使用注解方式
 * redisTemplate 模版就像是使用redis 工具类一样，两者都是缓存。
 */
@RestController
@Validated //参数约束注解
@RequestMapping({"api/v2/SimpleCache"})
public class SimpleCacheController {
    /**
     * 演示通过不同的参数来测试缓存效果，参数不同，就会重新缓存一个新的key
     *
     * @param p1
     * @param p2
     */
    @Cacheable("Cache")
    @GetMapping("AddCache")
    public MsfResponse AddCache(@RequestParam @Parameter(description = "p1参数") @NotNull @Size(min = 1, max = 2) String p1,
                                @RequestParam @Parameter(description = "p2参数") @NotNull @Size(min = 1, max = 64) String p2) {
        return MsfResponse.success(String.join(p1, p2));
    }
}
