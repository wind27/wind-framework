package com.wind.dao;

/**
 * IBaseDao
 *
 * @author qianchun 17/7/13
 **/
public interface IBaseDao<T, PK> {
    /**
     * 新增
     * @param t 待插入对象
     * @return 返回插入结果
     */
    T insert(T t);

    /**
     * 更新
     * @param t 待更新对象
     * @return 返回执行结果
     */
    boolean update(T t);

    /**
     * 根据主键查询对象
     * @param pk 主键ID
     * @return 返回对象
     */
    T findById(PK pk);

    /**
     * 根据主键删除对象
     * @param pk 主键ID
     * @return 返回执行结果
     */
    boolean deleteById(PK pk);
}
