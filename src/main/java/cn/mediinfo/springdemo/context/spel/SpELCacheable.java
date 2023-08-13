package cn.mediinfo.springdemo.context.spel;

import cn.mediinfo.springdemo.model.ClientscopeEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.Map;

/**
 * 此示例演示SpEL在Spring Cacheable中的用法
 *
 * @Cacheable：在方法执行前查看是否有缓存对应的数据，如果有直接返回数据，如果没有调用方法获取数据返回，并缓存起来。
 * @CacheEvict：将一条或多条数据从缓存中删除。
 * @CachePut：将方法的返回值放到缓存中
 * @EnableCaching：开启缓存注解功能
 * @Caching：组合多个缓存注解；
 * @CacheConfig：统一配置@Cacheable中的value值
 */
public class SpELCacheable {

    /**
     * 在 @Cacheable 中的SpEL缓存示例
     * Springcache 提供两个参数来指定缓存名：value、cacheNames，二者选其一即可,每一个需要缓存的数据都需要指定要放到哪个名字的缓存，缓存的分区，按照业务类型分
     * key：缓存的key，如果是redis，则相当于redis的key
     * unless 指定一个 SpEL 表达式，用于判断是否禁止缓存。只有当表达式的值为 true 时，才会禁止缓存。默认为空字符串，表示不禁止缓存。
     * condition：指定一个 SpEL 表达式，用于判断是否执行缓存操作。只有当表达式的值为 true 时，才会进行缓存操作。默认为空字符串，表示不做条件限制。
     * keyGenerator：指定一个自定义的缓存键生成器（KeyGenerator）的 Bean 名称，用于替代默认的键生成策略。可以通过实现 KeyGenerator 接口来创建自定义的键生成器。
     * cacheManager：指定一个自定义的缓存管理器（CacheManager）的 Bean 名称，用于替代默认的缓存管理器。可以通过实现 CacheManager 接口来创建自定义的缓存管理器。
     * @return
     */
    @Cacheable(value = {"Application","Application2"},key = "# roor.method", unless = "# result == null || result.isEmpty()")
    public Map<String, Map<String, String>> getApplicationGlobalSetting() {
        return null;
    }

    /**
     *在 @Caching 中的SpEL缓存示例
     * evict：用于配置一个或多个 @CacheEvict 注解，表示清除缓存中的数据。通过该属性来指定要使用的缓存名称、键生成策略、条件判断等。
     * put：用于配置一个或多个 @CachePut 注解，表示更新缓存中的数据。通过该属性来指定要使用的缓存名称、键生成策略等。
     * cacheable：用于配置一个或多个 @Cacheable 注解，表示启用方法结果的缓存。可以通过该属性来指定要使用的缓存名称、键生成策略、条件判断等。
     * cachePut：用于配置一个或多个 @CachePut 注解，与 put 属性功能相同，可作为 put 的别名使用。
     * cacheEvict：用于配置一个或多个 @CacheEvict 注解，与 evict 属性功能相同，可作为 evict 的别名使用。
     * @param scope
     * @return
     */
    @Caching(evict = {@CacheEvict(value = "Application",key = "# roor.method")})
    public String GetScopeName(ClientscopeEntity scope) {
        return scope.getScope();
    }

    /**
     * 在 @CachePut 中的SpEL缓存示例
     * value 或 cacheNames：指定要使用的缓存名称，可以是一个字符串数组，用逗号分隔。如果指定了多个缓存名称，那么方法返回的结果将会被存储在这些缓存中。
     * key：指定缓存的键（Key）。默认情况下，Spring 将使用方法的所有参数作为缓存的键。可以通过 SpEL 表达式自定义缓存键的生成策略。
     * condition：指定一个 SpEL 表达式，用于判断是否执行缓存操作。只有当表达式的值为 true 时，才会进行缓存操作。默认为空字符串，表示不做条件限制。
     * unless：指定一个 SpEL 表达式，用于判断是否禁止缓存。只有当表达式的值为 true 时，才会禁止缓存。默认为空字符串，表示不禁止缓存。
     * keyGenerator：指定一个自定义的缓存键生成器（KeyGenerator）的 Bean 名称，用于替代默认的键生成策略。可以通过实现 KeyGenerator 接口来创建自定义的键生成器。
     * cacheManager：指定一个自定义的缓存管理器（CacheManager）的 Bean 名称，用于替代默认的缓存管理器。可以通过实现 CacheManager 接口来创建自定义的缓存管理器。
     * @param scope
     * @return
     */
    @CachePut(value ="Application",condition = "# result = null && !result.isEmpty()")
    public String GetScopeName2(ClientscopeEntity scope) {
        return scope.getScope();
    }
}
