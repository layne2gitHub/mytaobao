package com.layne.my.taobao.commons.persistnce;

import java.util.List;

public interface BaseTreeDao<T extends BaseEntity> {
    /**
     * 查询全部信息
     * @return
     */
    List<T> selectAll();

    void insert(T entity);

    void delete(Long id);

    T getById(Long id);

    void update(T entity);

    List<T> selectByPid(Long pId);
}
