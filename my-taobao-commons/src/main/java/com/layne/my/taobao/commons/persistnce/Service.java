package com.layne.my.taobao.commons.persistnce;

import com.layne.my.taobao.commons.dto.BaseResult;
import com.layne.my.taobao.commons.dto.PageInfo;

import java.util.List;

public interface Service<T extends BaseEntity> {

    /**
     * 查询全部信息
     * @return
     */
    List<T> selectAll();

    BaseResult save(T entity);

    void delete(Long id);

    T getById(Long id);

    void update(T entity);

    PageInfo<T> page(int start, int length, int draw, T entity);
}
