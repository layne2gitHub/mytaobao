package com.layne.my.taobao.web.admin.abstracts;

import com.layne.my.taobao.commons.dto.PageInfo;
import com.layne.my.taobao.commons.persistnce.BaseEntity;
import com.layne.my.taobao.commons.persistnce.BaseTreeDao;
import com.layne.my.taobao.commons.persistnce.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 通用抽象Service实现方法类
 */
public abstract class AbstractBaseTreeServiceImpl<T extends BaseEntity, D extends BaseTreeDao<T>> implements BaseTreeService<T> {

    @Autowired
    protected D dao;
    /**
     * 查询全部信息
     *
     * @return
     */
    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        dao.delete(id);
    }
    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(T entity) {
        dao.update(entity);
    }
    @Override
    public List<T> selectByPid(Long pId){
        return dao.selectByPid(pId);
    }

    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        return null;
    }
}
