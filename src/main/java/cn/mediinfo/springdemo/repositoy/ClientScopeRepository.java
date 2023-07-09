
package cn.mediinfo.springdemo.repositoy;

import cn.mediinfo.springdemo.model.ClientscopeEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ClientScopeRepository extends JpaRepository<ClientscopeEntity,String> {
    @Transactional(timeout = 10)
    @Query("select c.id from ClientscopeEntity as c")
    List<String> Get();

    /**
     * 根据ID分页查询
     * @param id
     * @param pageable
     * @return 返回一个带分页结果的page,带count（会执行一条count的SQL语句），性能低
     */
    Page<ClientscopeEntity> findByid(String id,Pageable pageable);

    /**
     * 根据ID分页查询，且只返回第一条
     * @param id
     * @param pageable
     * @return 返回一个带分页结果的page,带count（会执行一条count的SQL语句），性能低
     */
    Page<ClientscopeEntity> findTop1Byid(String id,Pageable pageable);

    /**
     * 根据ID分页查询，且只返回前5条
     * @param id
     * @param pageable
     * @return 返回一个带分页结果的page,带count（会执行一条count的SQL语句），性能低
     */
    Page<ClientscopeEntity> findFirst5Byid(String id,Pageable pageable);

    /**
     * 根据ID分页查询，且去重后只返回前5条
     * @param id
     * @param pageable
     * @return 返回一个带分页结果的page,带count（会执行一条count的SQL语句），性能低
     */
    Page<ClientscopeEntity> findDistinctFirst5Byid(String id,Pageable pageable);

    /**
     * 根据ID和Clientid分页查询,且带排序
     * @param id
     * @param pageable
     * @return 返回一个带分页结果的Slice,只知道下一个Slice是否不可以，不带count，性能高
     */
    Slice<ClientscopeEntity> findByidAndClientid(String id,Pageable pageable, Sort sort);

    /**
     * 获取一个对象
     * 根据ID查询，且根据ID倒序
     * @Nonnull 要求参数和返回值不能为空
     * @param id must not be {@literal null}.
     * @return
     */
    @Nonnull
    ClientscopeEntity findTopByIdOrderByIdDesc(String id);

    /**
     * 获取一个对象
     * 根据ID查询，且根据ID倒序
     * @Nullable 参数和返回值允许为null
     * @param id
     * @return
     */
    @Nullable
    ClientscopeEntity findTopByIdOrderByIdAsc(String id);

    /**
     * 获取一个对象
     * 根据ID查询，且根据Clientid正序,参数不能为null，返回值可以为Null
     * @Nonnull 要求参数和返回值不能为空，但是Optional<T>声明返回值可以为null
     * @param id
     * @return
     */
    @Nonnull
   Optional<ClientscopeEntity> findTopByIdOrderByClientidAsc(String id);
}
