package com.wind.common;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * MybatisConfig
 *
 * @author qianchun 17/7/18
 **/

public class SqlSessionProcessor {

    @Autowired
    DataSourcesPool dataSourcesPool;

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

    private static Logger logger = LoggerFactory.getLogger(SqlSessionProcessor.class);

    private static Map<String, SqlSession> catalogSqlSessionMap = new HashMap<String, SqlSession>();
    /**
     * 获取 sqlSession
     *
     * @param catalog 数据源 catalog
     * @return 返回 sqlSession
     */
    private SqlSession getSqlSession(String catalog) {
        try {
            SqlSession sqlSession =catalogSqlSessionMap.get(catalog);
            if(sqlSession!=null) {
                return sqlSession;
            }

            DataSource dataSource = dataSourcesPool.getDataSource(catalog);
            sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);//指定数据源(这个必须有，否则报错)

            sqlSession = sqlSessionFactoryBean.getObject().openSession();
            //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//            fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//指定基包
//            fb.setMapperLocations(new PathMatchingResourcePatternResolver()
//                    .getResources(env.getProperty("mybatis.mapperLocations")));//指定xml文件位置

//            SqlSession sqlSession = fb.getObject().openSession();
//            sqlSession.getMapper()
//            fb.setConfigLocation(new ClassPathResource("mybatis-config.xml"));//指定mybatis配置

            return sqlSession;
        } catch (Exception e) {
            logger.error("[mybatis 配置] 创建 sqlSession 异常= {}", e);
            return null;
        }
    }
//
//    /**
//     * 创建 sqlSession
//     *
//     * @param dbName 数据库库名
//     * @return 返回 sqlSession
//     */
//    public SqlSession createSqlSession(String dbName) {
//        return getSqlSession(dbName);
//    }
}
