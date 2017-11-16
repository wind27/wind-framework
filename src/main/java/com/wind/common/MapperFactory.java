package com.wind.common;

import com.wind.mapper.Mapper;

/**
 * @author yangying
 * @date 2017/11/15.
 */
public class MapperFactory {

    public <T> T createMapper(Class<? extends Mapper> clazz, DataSourceEnvironment env) {
        return null;
    }
}
