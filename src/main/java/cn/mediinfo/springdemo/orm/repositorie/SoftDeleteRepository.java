package cn.mediinfo.springdemo.orm.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * 自定义Repositories
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface SoftDeleteRepository<T,ID extends Serializable> extends JpaRepository<T,ID> {
    void softDelete(T entity) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
