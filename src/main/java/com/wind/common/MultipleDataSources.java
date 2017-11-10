package com.wind.common;

import com.wind.annotation.DAO;
import com.wind.dao.impl.BaseDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * MultipleDataSources
 *
 * @author qianchun 17/7/24
 **/
public class MultipleDataSources {
    private static Logger logger = LoggerFactory.getLogger(MultipleDataSources.class);

    private static MybatisConfig mybatisConfig;

    private static Map<String, SqlSession> sqlSessionMap = new HashMap<String, SqlSession>();

    //初始化 Dao 数据源
    public static boolean init() {
        Set<String> daoBeanNameSet = SpringApplicationContextAware.getDAOBeanName();


        if(daoBeanNameSet!=null && daoBeanNameSet.size()>0) {
//        if(CollectionUtils.isNotEmpty(daoBeanNameSet)) {
            for(String daoName : daoBeanNameSet) {
                BaseDao dao = (BaseDao) SpringApplicationContextAware.getBean(daoName);

                if(!dao.getClass().isAnnotationPresent(DAO.class)) {
                    return false;
                }
                String dbName = dao.getClass().getAnnotation(DAO.class).catalog();

                SqlSession sqlSession = sqlSessionMap.get(dbName);
                if(sqlSession==null) {
                    if(mybatisConfig==null) {
                        mybatisConfig = (MybatisConfig) SpringApplicationContextAware.getBean("mybatisConfig");
                    }
                    sqlSession = mybatisConfig.createSqlSession(dbName);


                }
                if(sqlSession==null) {
                    logger.error(dao.getClass().getName() + " can not find database : "+ dbName);
                    return false;
                } else {
                    sqlSessionMap.put(dbName, sqlSession);
                    dao.setSqlSession(sqlSession);

                }
            }
        }
        logger.info(" Dao multiple data source init finish !!!");
        return true;
    }
}
