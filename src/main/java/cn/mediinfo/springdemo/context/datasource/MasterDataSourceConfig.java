package cn.mediinfo.springdemo.context.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

/**
 * @link https://blog.csdn.net/qq_40161813/article/details/126328265
 * jpa自动配置类 JpaBaseConfiguration.
 * 配置master数据源
 * *
 */
@Configuration
//@EnableTransactionManagement//开启事务

// 利⽤ EnableJpaRepositories 配置哪些包下⾯的 Repositories，采⽤哪个 EntityManagerFactory 和哪个 TransactionManagement
//@EnableJpaRepositories(
//        basePackages = "cn.mediinfo.springdemo.repositoy", // master 数据源的 repository 的包路径
//        transactionManagerRef = "masterTransactionManager", // 改变 master 数据源的 EntityManagerFactory 的默认值，改为 masterEntityManagerFactory
//        entityManagerFactoryRef = "masterEntityManagerFactory") //改变 master 数据源的 TransactionManagement 的默认值，masterTransactionManager
public class MasterDataSourceConfig {

    /**
     * 配置master数据源DataSource配置
     * @return
     */
    @Primary  //如果没有明确指定使用哪个 Bean，Spring 将会选择被 @Primary 注解标记的 Bean 作为首选项
    @Bean(name = "MasterDataSourceProperties") //配置Bean的名称，默认就是DataSourceProperties
    @ConfigurationProperties(prefix = "spring.master")//读取配置文件节
    public DataSourceProperties DataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 创建DataSource，可以选择不同的数据源，这⾥使⽤ HikariDataSource，创建数据源
     * DruidDataSource、HikariDataSource
     * @param dataSourceProperties  配置master数据源DataSource配置
     * @return  datasource实例
     */
    @Primary
    @Bean(name = "MasterDataSource")
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource DataSource(@Qualifier("MasterDataSourceProperties") DataSourceProperties dataSourceProperties)
    {
        //创建datasource实例
        DruidDataSource dataSource=dataSourceProperties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
        return dataSource;
    }

//    /**
//     * 配置 master 数据源的 entityManagerFactory 命名为 masterEntityManagerFactory，⽤来对实体进⾏⼀些操作
//     * @param builder 构建器
//     * @param masterDataSource masterDataSource master 数据源
//     * @return 实体管理工厂
//     */
//    @Primary
//    @Bean(name = "masterEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
//                                                                       @Qualifier("MasterDataSource") DataSource masterDataSource)
//    {
//       return builder.dataSource(masterDataSource).build();
//    }
//
//    /**
//     * 配置 master 数据源的事务管理者，命名为 masterTransactionManager 依赖 masterEntityManagerFactory
//     *
//     * @param masterEntityManagerFactory master 实体管理工厂
//     * @return master 事务管理者
//     */
//    @Primary
//    @Bean(name = "masterTransactionManager")
//    public PlatformTransactionManager transactionManager(@Qualifier("masterEntityManagerFactory") EntityManagerFactory masterEntityManagerFactory) {
//        return new JpaTransactionManager(masterEntityManagerFactory);
//    }


}
