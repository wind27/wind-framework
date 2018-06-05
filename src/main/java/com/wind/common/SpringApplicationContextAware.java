package com.wind.common;

import com.wind.annotation.DAO;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.Set;

/**
 * SpringApplicationContextAware
 *
 * @author qianchun 17/7/24
 **/
//@Component
public class SpringApplicationContextAware /*implements ApplicationContextAware */{
//    private static ApplicationContext applicationContext;
//
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        if(this.applicationContext == null) {
//            this.applicationContext = applicationContext;
//            //初始化 dao 数据源
//            DataSourcesPool.init();
//        }
//    }
//
//    //获取applicationContext
//    public static ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }
//
//    //通过name获取 Bean.
//    public static Object getBean(String name){
//        return getApplicationContext().getBean(name);
//    }
//
//    public static Set<String> getDAOBeanName() {
//        Map<String, Object> map = applicationContext.getBeansWithAnnotation(DAO.class);
//        return map.keySet();
//    }
//
//    //通过class获取Bean.
//    public static <T> T getBean(Class<T> clazz){
//        return getApplicationContext().getBean(clazz);
//
//    }
//
//    //通过name,以及Clazz返回指定的Bean
//    public static <T> T getBean(String name,Class<T> clazz){
//        return getApplicationContext().getBean(name, clazz);
//    }
}
