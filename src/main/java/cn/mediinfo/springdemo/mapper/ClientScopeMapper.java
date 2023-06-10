package cn.mediinfo.springdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * mybatis示例
 */
@Mapper //让 MyBatis 自动生成该接口的实现类。
@Repository
public interface ClientScopeMapper {
    List<String> Get();
}
