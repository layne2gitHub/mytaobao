package com.layne.my.taobao.commons.persistnce;


import java.util.List;
import java.util.Map;

public interface BaseDao<T extends BaseEntity> {
    /**
     * 查询全部信息
     * @return
     */
    List<T> selectAll();

    void insert(T entity);

    void delete(Long id);

    T getById(Long id);

    void update(T entity);
    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param  params 需要两个参数 start记录开始位置/length每页数据数量
     * @return
     */
    List<T> page(Map<String, Object> params);

    /**
     * 查询数据总数目
     */
    int countTotal(T entity);
}
