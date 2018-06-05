package com.wind.common;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * DataSourcesPool 数据库连接池
 *
 * @author qianchun 17/7/24
 **/
@Component
public class DataSourcesPool {
    private static Logger logger = LoggerFactory.getLogger(DataSourcesPool.class);

    @Autowired
    private Environment env;

    private static Map<String, DataSource> cataloagDataSourceMap = new HashMap<String, DataSource>();

    /**
     * 获取数据源
     *
     * @param catalog dataSource id 前缀
     * @return 返回结果
     */
    public DataSource getDataSource(String catalog) {
        try {
            DataSource dataSource = cataloagDataSourceMap.get(catalog);
            if (dataSource != null) {
                return dataSource;
            }
            Properties databaseProp = new Properties();
            databaseProp.put("driverClassName", env.getProperty(catalog + ".jdbc.driverClassName"));
            databaseProp.put("url", env.getProperty(catalog + ".jdbc.url"));
            databaseProp.put("username", env.getProperty(catalog + ".jdbc.username"));
            databaseProp.put("password", env.getProperty(catalog + ".jdbc.password"));
            databaseProp.put("initialSize", 1);
            databaseProp.put("maxActive", 80);
            databaseProp.put("minIdle", 10);
            databaseProp.put("maxWait", 60000);
            databaseProp.put("filters", "mergeStat");
            databaseProp.put("logAbandoned", true);
            databaseProp.put("testOnBorrow", false);
            databaseProp.put("testOnReturn", false);
            databaseProp.put("testWhileIdle", true);
            databaseProp.put("removeAbandoned", true);
            databaseProp.put("poolPreparedStatements", false);
            databaseProp.put("validationQuery", "SELECT 1 ");
            databaseProp.put("removeAbandonedTimeout", 1800);
            databaseProp.put("timeBetweenEvictionRunsMillis", 60000);
            databaseProp.put("minEvictableIdleTimeMillis", 25200000);
            databaseProp.put("maxPoolPreparedStatementPerConnectionSize", 200);
            dataSource = DruidDataSourceFactory.createDataSource(databaseProp);
            cataloagDataSourceMap.put(catalog, dataSource);
            return dataSource;
        } catch (Exception e) {
            logger.info("[datasource] add datasource exception : ", e);
            return null;
        }
    }
}
