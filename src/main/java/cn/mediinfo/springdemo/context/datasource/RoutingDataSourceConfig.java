package cn.mediinfo.springdemo.context.datasource;

import cn.mediinfo.springdemo.orm.entity.MsfEntity;
import com.google.common.collect.Maps;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @link https://blog.csdn.net/qq_40161813/article/details/126328265
 */
@Configuration
@EnableTransactionManagement//开启事务
@RequiredArgsConstructor

// 利⽤ EnableJpaRepositories 配置哪些包下⾯的 Repositories，采⽤哪个 EntityManagerFactory 和哪个 TransactionManagement
@EnableJpaRepositories(
        basePackages = "cn.mediinfo.springdemo.repositoy", //数据源的 repository 的包路径
        transactionManagerRef = "routingTransactionManager", //改变数据源的 EntityManagerFactory 的默认值，改为 routingTransactionManager
        entityManagerFactoryRef = "routingEntityManagerFactory") //改变数据源的 TransactionManagement 的默认值，routingEntityManagerFactory
public class RoutingDataSourceConfig {

    //1.@RequiredArgsConstructor 构造方法注入
    //2.在一个 Spring 容器中，可能存在多个类型相同的 Bean，使用 @Qualifier 可以告诉 Spring 在注入依赖时使用特定的 Bean。
    @Qualifier("MasterDataSource")
    private final DataSource MasterDataSource;

    @Bean("routingDataSource")
    public DataSource DataSource()
    {
        Map<Object,Object> DataSourceMap= Maps.newHashMap();
        DataSourceMap.put("MasterDataSource",MasterDataSource);
        DataSourceMap.put("SlaveDataSource",MasterDataSource);

        RoutingDataSource  routingDataSource=new RoutingDataSource();
        // 设置 RoutingDataSource 的数据源清单
        routingDataSource.setTargetDataSources(DataSourceMap);
        // 设置 RoutingDataSource 的默认数据源
        routingDataSource.setDefaultTargetDataSource(MasterDataSource);

        return routingDataSource;
    }

    /**
     * 配置数据源的 entityManagerFactory 命名为 routingEntityManagerFactory，⽤来对实体进⾏⼀些操作
     * @param builder 构建器
     * @param routingDataSource 的数据源
     * @return 实体管理工厂
     */
    @Primary
    @Bean(name = "routingEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("routingDataSource") DataSource routingDataSource)
    {
        return builder
                .dataSource(routingDataSource)
                .packages("cn.mediinfo.springdemo.model")
                .persistenceUnit("routingDataSource") //持久性单元的名称。如果只构建一个 EntityManagerFactory，则可以省略它，但如果同一应用程序中有多个实体管理器，则应为它们指定不同的名称
                .build();
    }

    /**
     * 配置数据源的事务管理者，命名为 routingTransactionManager 依赖 routingEntityManagerFactory
     *
     * @param routingEntityManagerFactory 实体管理工厂
     * @return routing 事务管理者
     */
    @Primary
    @Bean(name = "routingTransactionManager")
        public PlatformTransactionManager transactionManager(@Qualifier("routingEntityManagerFactory") EntityManagerFactory routingEntityManagerFactory) {
        return new JpaTransactionManager(routingEntityManagerFactory);
    }
}
