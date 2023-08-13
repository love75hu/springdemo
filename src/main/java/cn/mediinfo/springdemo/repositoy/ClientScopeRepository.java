
package cn.mediinfo.springdemo.repositoy;

import cn.mediinfo.springdemo.model.ClientscopeEntity;
import cn.mediinfo.springdemo.orm.repositorie.SoftDeleteRepository;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.annotation.Nonnull;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface ClientScopeRepository extends SoftDeleteRepository<ClientscopeEntity, String> {
    /**
     * JPQL语法查询
     *
     * @param id
     * @return
     */
    @Transactional(timeout = 10)
    @Query(value = "select c.id from ClientscopeEntity as c where c.id = :id", nativeQuery = false)
    List<String> findAllById(@Param("id") String id);

    Optional<ClientscopeEntity> findById(String id);


    /**
     * 根据ID分页查询，且只返回第一条
     *
     * @param id
     * @param pageable
     * @return 返回一个带分页结果的page, 带count（会执行一条count的SQL语句），性能低
     */
    Page<ClientscopeEntity> findTop1Byid(String id, Pageable pageable);

    /**
     * 根据ID分页查询，且只返回前5条
     *
     * @param id
     * @param pageable
     * @return 返回一个带分页结果的page, 带count（会执行一条count的SQL语句），性能低
     */
    Page<ClientscopeEntity> findFirst5Byid(String id, Pageable pageable);

    /**
     * 根据ID分页查询，且去重后只返回前5条
     *
     * @param id
     * @param pageable
     * @return 返回一个带分页结果的page, 带count（会执行一条count的SQL语句），性能低
     */
    Page<ClientscopeEntity> findDistinctFirst5Byid(String id, Pageable pageable);

    /**
     * 根据ID和Clientid分页查询,且带排序
     * @param id
     * @param pageable
     * @return 返回一个带分页结果的Slice, 只知道下一个Slice是否不可以，不带count，性能高
     */
    //Slice<ClientscopeEntity> findByidAndClientid(String id,Pageable pageable, Sort sort);

    /**
     * 获取一个对象
     * 根据ID查询，且根据ID倒序
     *
     * @param id must not be {@literal null}.
     * @return
     * @Nonnull 要求参数和返回值不能为空
     */
    @Nonnull
    ClientscopeEntity findTopByIdOrderByIdDesc(String id);

    /**
     * 获取一个对象
     * 根据ID查询，且根据ID倒序
     *
     * @param id
     * @return
     * @Nullable 参数和返回值允许为null
     */
    @Nullable
    ClientscopeEntity findTopByIdOrderByIdAsc(String id);

    /**
     * 获取一个对象
     * 根据ID查询，且根据Clientid正序,参数不能为null，返回值可以为Null
     *
     * @param id
     * @return
     * @Nonnull 要求参数和返回值不能为空，但是Optional<T>声明返回值可以为null
     */
    @Nonnull
    Optional<ClientscopeEntity> findTopByIdOrderByClientidAsc(String id);

    /**
     * ueryByExampleExecutor  用法示例
     * 特点：通过 ueryByExampleExecutor 指定我们需要匹配的字段，是否忽略大小写，匹配规则等，而不是写SQL或者通过方法名 findTopByIdOrderByClientidAsc 这样来定义规则
     * <S extends ClientscopeEntity> 声明了一个继承ClientscopeEntity 的泛型类，这样如果是ClientscopeEntity的子类就不用再实现同样的逻辑
     * @param example
     * @return
     */
    default <S extends ClientscopeEntity> Optional<ClientscopeEntity> findOneByExample(ClientscopeEntity example, Class<S> EntityClass) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id") // 忽略 id 属性
                .withIgnoreCase(true) // 忽略大小写
                .withIgnoreNullValues()//如果NULL值的Property则忽略不参与过滤（默认）
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // 模糊匹配
        /*
            DEFAULT  不忽略大小写，对应JPQL语法 firstname=?1
            EXACT    忽略大小写， 对应JPQL语法  LOWER(firstname)=LOWER(?1)
            STARTING 忽略大小写， 对应JPQL语法  LOWER(firstname) like LOWER(?0)+'%'
            ENDING   不忽略大小写，对应JPQL语法 firstname like '%'+?1
            CONTAINING 不忽略大小写，对应JPQL语法 firstname like '%'+?1+'%'
         */

        if (EntityClass == ClientscopeEntity.class) {
            Example<ClientscopeEntity> entityExample = Example.of(example, matcher);
            return findOne(entityExample);
        } else {
            throw new NotImplementedException("该子类未支持查询！");
        }
    }
}
