package com.layne.my.taobao.web.admin.abstracts;

import com.layne.my.taobao.commons.dto.PageInfo;
import com.layne.my.taobao.commons.persistnce.BaseDao;
import com.layne.my.taobao.commons.persistnce.BaseEntity;
import com.layne.my.taobao.commons.persistnce.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseServiceImpl<T extends BaseEntity,D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

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
    public void update(T tbContent) {
        dao.update(tbContent);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteMulti(String[] ids) {
        dao.deleteMulti(ids);
    }

    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        Map<String,Object> map=new HashMap<>();
        map.put("start",start);
        map.put("length",length);
        map.put("pageParams",entity);
        int countTotal = countTotal(entity);
        PageInfo<T> pageInfo=new PageInfo<T>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(countTotal);
        pageInfo.setRecordsFiltered(countTotal);
        pageInfo.setData(dao.page(map));
        return pageInfo;
    }

    @Override
    public int countTotal(T tbContent) {
        return dao.countTotal(tbContent);
    }
}
