package com.wind.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * DynamicDataSource
 *
 * @author qianchun
 * @date 2018/4/20
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    protected Object determineCurrentLookupKey() {
        String catalog = DynamicDataSourceContextHolder.getDataSourceKey();
        logger.info("Current DataSource catalog is [{}]", catalog);
        return catalog;
    }
}
