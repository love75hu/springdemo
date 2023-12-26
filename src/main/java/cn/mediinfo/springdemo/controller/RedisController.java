//package cn.mediinfo.springdemo.controller;
//
//import cn.mediinfo.springdemo.response.MsfResponse;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import jakarta.annotation.Resource;
//import jakarta.validation.constraints.NotNull;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.core.*;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
///**
// * 使用redis模版操作缓存
// */
//@RestController
//@Slf4j
//@RequestMapping("api/v1/Redis")
//@RequiredArgsConstructor
//@Validated //参数约束注解
//public class RedisController {
//    private final StringRedisTemplate ReactiveRedis;
//    private final RedisTemplate<String,String> ReactiveRedis2;
//    @Resource(name = "redisTemplate")
//    private ValueOperations<String,String> ValueOperations2;
//    /**
//     * 使用 StringRedisTemplate 获取 ValueOperations
//     *
//     * @param key
//     * @return
//     */
//    @Operation(summary = "根据key获取值")
//    @GetMapping("StringGet")
//    public MsfResponse StringGet(@Parameter(description = "key") String key) {
//        ValueOperations<String, String> ValueOperations = ReactiveRedis.opsForValue();
//        return MsfResponse.success(ValueOperations.get(key));
//    }
//
//    /**
//     * 使用 直接获取 ValueOperations
//     * @param key
//     * @return
//     */
//    @Operation(summary = "根据key获取值2")
//    @GetMapping("StringGet2")
//    public MsfResponse StringGet2(@Parameter(description = "key") String key) {
//        return MsfResponse.success(ValueOperations2.get(key));
//    }
//
//    /**
//     * 使用 StringRedisTemplate 获取 ValueOperations
//     *
//     * @param key
//     * @param value
//     */
//    @Operation(summary = "根据Key设置值")
//    @PostMapping("StringSet")
//    public void StringSet(
//            @RequestParam @Parameter(description = "key") @NotNull String key,
//            @RequestParam @Parameter(description = "value") @NotNull String value) {
//        ValueOperations<String, String> ValueOperations = ReactiveRedis2.opsForValue();
//        ValueOperations.set(key, value);
//    }
//}
