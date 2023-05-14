package cn.mediinfo.springdemo.repositoy;

import cn.mediinfo.springdemo.model.ClientscopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientScopeRepository extends JpaRepository<ClientscopeEntity,String> {
    @Transactional(timeout = 10)
    @Query("select c.id from ClientscopeEntity as c")
    List<String> Get();
}
