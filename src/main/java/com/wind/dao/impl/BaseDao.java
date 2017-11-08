package com.wind.dao.impl;

import com.wind.dao.IBaseDao;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * BaseDao
 *
 * @author qianchun 17/7/13
 **/
public class BaseDao<T, PK> implements IBaseDao<T, PK> {
    private SqlSession sqlSession;

    private String nameSpace;
    {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            @SuppressWarnings("unchecked")
            Class<T> clazz = (Class<T>) pType.getActualTypeArguments()[0];
            nameSpace = clazz.getName();
        } else {
            nameSpace = null;
        }
        if (nameSpace == null) {
            throw new RuntimeException("no name space specialed for mybaits");
        }
    }

    /**
     * 新增
     * 
     * @param t 待插入对象
     * @return 返回新增结果
     */
    public T insert(T t) {
        return sqlSession.insert(String.format("%s.insert", nameSpace), t) > 0 ? t : null;
    }

    /**
     * 批量新增
     * 
     * @param tList 待插入列表
     * @return 返回执行结果
     */
    public boolean batchInsert(List<T> tList) {
        int result = sqlSession.insert(String.format("%s.batchInsert", nameSpace), tList);
        return result > 0;
    }

    /**
     * 更新
     * 
     * @param t 待更新对象
     * @return 返回执行结果
     */
    public boolean update(T t) {
        int result = sqlSession.update(String.format("%s.update", nameSpace), t);
        return result > 0;
    }

    /**
     * 根据主键查询对象
     * 
     * @param pk 主键ID
     * @return 返回执行结果
     */
    public T findById(PK pk) {
        return sqlSession.selectOne(String.format("%s.findById", nameSpace), pk);
    }

    /**
     * 根据主键删除对象
     * 
     * @param pk 主键ID
     * @return 返回执行结果
     */
    public boolean deleteById(PK pk) {
        int result = sqlSession.delete(String.format("%s.deleteById", nameSpace), pk);
        return result > 0;
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
