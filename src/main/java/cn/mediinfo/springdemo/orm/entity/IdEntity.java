package cn.mediinfo.springdemo.orm.entity;

/**
 * 带id的实体基类，类型为T
 * @param <T>
 */
public interface IdEntity<T> extends Entity {
     T getId() ;

     void setId(T id) ;
}
