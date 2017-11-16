package com.wind.common;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * MybatisConfig
 *
 * @author qianchun 17/7/18
 **/

@Configuration
@MapperScan(basePackages = "com.wind.*.model")
public class MybatisConfig {

    private static Logger logger = LoggerFactory.getLogger(MybatisConfig.class);

    @Autowired
    private Environment env;

    /**
     * 获取数据源
     *
     * @param dbName 数据库库名
     * @return 返回 dataSource
     */
    private DataSource getDataSource(String dbName) {
        try {
            Properties userDatabaseProp = new Properties();
            userDatabaseProp.put("driverClassName", env.getProperty(dbName + ".jdbc.driverClassName"));
            userDatabaseProp.put("url", env.getProperty(dbName + ".jdbc.url"));
            userDatabaseProp.put("username", env.getProperty(dbName + ".jdbc.username"));
            userDatabaseProp.put("password", env.getProperty(dbName + ".jdbc.password"));
            return DruidDataSourceFactory.createDataSource(userDatabaseProp);
        } catch (Exception e) {
            logger.error("[mybatis 配置] 创建 DataSource 异常= {}", e);
            return null;
        }
    }

    /**
     * 获取 sqlSession
     *
     * @param dbName 数据库库名
     * @return 返回 sqlSession
     */
    private SqlSession getSqlSession(String dbName) {
        try {
            DataSource dataSource = getDataSource(dbName);
            SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
            fb.setDataSource(dataSource);//指定数据源(这个必须有，否则报错)
            //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//            fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//指定基包
//            fb.setMapperLocations(new PathMatchingResourcePatternResolver()
//                    .getResources(env.getProperty("mybatis.mapperLocations")));//指定xml文件位置

//            SqlSession sqlSession = fb.getObject().openSession();
//            sqlSession.getMapper()
//            fb.setConfigLocation(new ClassPathResource("mybatis-config.xml"));//指定mybatis配置

            return fb.getObject().openSession();
        } catch (Exception e) {
            logger.error("[mybatis 配置] 创建 sqlSession 异常= {}", e);
            return null;
        }

    }

    /**
     * 创建 sqlSession
     *
     * @param dbName 数据库库名
     * @return 返回 sqlSession
     */
    public SqlSession createSqlSession(String dbName) {
        return getSqlSession(dbName);
    }
}
