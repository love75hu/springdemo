package cn.mediinfo.springdemo.repositoy;

import cn.mediinfo.springdemo.model.ClientscopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * 演示一些SpEL的一些用法
 * spel 在@Query 使用示例
 */
public interface SpELRepository extends JpaRepository<ClientscopeEntity,String> {
    /**
     * 根据下标获取参数
     * 此示例演示了SpEL和:Param 混用方式
     * ?1是获取下标第一个参数
     * :Id是参数占位符
     * ?#{'22'}是一个固定参数值
     * @param Id
     * @return
     */
    @Query("select c from ClientscopeEntity c where c.scope=?1 and c.id = ?2 or c.scope='22'")
    List<ClientscopeEntity> findByScopeaAndId(String scope,String Id);

    /**
     * 利用#获取QueryParam里面的参数
     * :#{ Id } 获取QueryParam里面的参数
     * ?#{principal.username} 获取认证当前用户的账号
     * @param Id
     * @return
     */
    @Query("select c from ClientscopeEntity c where c.id = :#{ Id } and c.scope=?#{principal.username}")
    List<ClientscopeEntity> findByScope2(@QueryParam("Id") String Id);

    /**
     * 使用JPA约定的变量entityName获取当前实体名称
     * @return
     */
    @Query(value = "select c from #{# entityName} c",nativeQuery = true)
    List<ClientscopeEntity> findAll();
}
