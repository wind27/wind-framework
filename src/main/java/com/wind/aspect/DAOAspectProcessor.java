package com.wind.aspect;

import com.wind.annotation.DAO;
import com.wind.common.DataSourcesPool;
import com.wind.common.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

import javax.sql.DataSource;

/**
 * DAOAspectProcessor
 *
 * @author qianchun
 * @date 2018/4/20
 **/

@Component
@Aspect
public class DAOAspectProcessor {
    private static Logger logger = LoggerFactory.getLogger(DAOAspectProcessor.class);

    private ApplicationContext applicationContext;

    @Autowired
    private DataSourcesPool dataSourcesPool;

    public DAOAspectProcessor() {
    }

    // 切面注解TXAction
    @Pointcut("execution( * com.wind.*.mapper.*.*(..))")
    public void daoAspect() {}

    @Before("daoAspect()")
    public void switchDataSource(JoinPoint point) {
        try {
            DAO  dao = (DAO) point.getSignature().getDeclaringType().getAnnotation(DAO.class);
            if (dao == null) {
                return;
            }
//            assertTransactional(dao.catalog());
            DataSource dataSource = dataSourcesPool.getDataSource(dao.catalog());
            DynamicDataSourceContextHolder.setDataSourceKey(dao.catalog());

            String dataSourceKey = DynamicDataSourceContextHolder.getDataSourceKey();

            String className = point.getTarget().getClass().getName();
            Signature methodName = point.getSignature();
            logger.info("Switch DataSource to [{}] in Method [{}]", dataSourceKey, className + "." + methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After("daoAspect()")
    public void restoreDataSource(JoinPoint point) {
        DynamicDataSourceContextHolder.clearDataSourceKey();
        logger.info("Restore DataSource to [{}] in Method [{}]", DynamicDataSourceContextHolder.getDataSourceKey(),
                point.getSignature());
    }

    private void assertTransactional(String catalog) throws Exception {
        DataSource dataSource = dataSourcesPool.getDataSource(catalog);
        Assert.notNull(dataSource, "datasource can not empty");
        ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
        if (!conHolder.isSynchronizedWithTransaction()) {
            logger.info("transaction connection is null");
            // throw new Exception("transaction connection is null");
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void onApplicationEvent(ApplicationEvent applicationEvent) {

    }

    public void setResourceLoader(ResourceLoader resourceLoader) {

    }

    public int getOrder() {
        return 0;
    }
}
