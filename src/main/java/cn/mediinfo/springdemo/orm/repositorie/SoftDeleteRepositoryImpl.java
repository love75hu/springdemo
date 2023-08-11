package cn.mediinfo.springdemo.orm.repositorie;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import me.prettyprint.cassandra.utils.Assert;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.util.ProxyUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Transactional(readOnly = true)
public class SoftDeleteRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements SoftDeleteRepository<T, ID> {
    private  EntityManager entityManager;
    private  JpaEntityInformation<T, ?> entityInformation;

    public SoftDeleteRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation=entityInformation;
        this.entityManager=entityManager;
    }

    @Override
    @Transactional
    public void softDelete(T entity) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Assert.notNull(entity,"entity不能未null");

        //如果entity状态New状态，则不能执行删除操作，只有Managed状态才可以
        if (entityInformation.isNew(entity)) {
            return;
        }

        //获取entity的类型
        Class<?> type= ProxyUtils.getUserClass(entity);

        //如果操作的实体为null，则不执行
       T existing= (T)entityManager.find(type,entityInformation.getId(entity));
       if (existing==null)
       {
           return;
       }

        Method method= existing.getClass().getMethod("setScope",String.class);
        method.setAccessible(true);
        method.invoke(existing, "删除测试1");
        entityManager.merge(existing);
    }
}
