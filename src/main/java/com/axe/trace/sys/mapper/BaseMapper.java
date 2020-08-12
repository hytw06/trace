package com.axe.trace.sys.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper基类
 */
public interface BaseMapper<T> {

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    T get(String id);

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    T get(T entity);

    /**
     * 查询数据列表
     * @param entity
     * @return
     */
    List<T> findList(T entity);

    /**
     * 插入数据
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 删除数据
     * @param entity
     * @return
     */
    int delete(T entity);

}
