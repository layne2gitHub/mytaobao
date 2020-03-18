package com.layne.my.taobao.commons.persistnce;

import com.layne.my.taobao.commons.dto.PageInfo;

public interface BaseService<T extends BaseEntity> extends Service<T> {
    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);
    /**
     * 分页查询
     * @return
     */
    PageInfo<T> page(int start, int length, int draw, T entity);

    /**
     * 查询数据总数目
     */
    int countTotal(T entity);
}
