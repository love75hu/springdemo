package cn.mediinfo.springdemo.context.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 用于实现动态数据源切换。通常在多数据源环境下使用，可以根据不同的条件（例如用户、请求参数等）来动态选择使用哪个数据源。
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

    /**
     * 确定当前使用的数据源key,存储在ThreadLocal
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSorceContextHolder.getPrefix();
    }
}
