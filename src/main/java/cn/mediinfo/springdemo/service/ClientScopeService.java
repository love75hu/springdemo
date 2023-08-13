package cn.mediinfo.springdemo.service;

import cn.mediinfo.springdemo.model.ClientscopeEntity;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

public interface ClientScopeService {

    @Query("select id from ClientscopeEntity ")
    List<String> Get();

    List<ClientscopeEntity> GetById(String Id);

    List<ClientscopeEntity> GetPageable(int Page, int Size);

    void  Delete(String Id) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;

   <S extends ClientscopeEntity> Optional<ClientscopeEntity> findOneByExample(ClientscopeEntity example, Class<S> EntityClass);
}
