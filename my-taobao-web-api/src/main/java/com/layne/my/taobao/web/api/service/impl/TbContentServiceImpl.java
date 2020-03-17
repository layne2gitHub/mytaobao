package com.layne.my.taobao.web.api.service.impl;

import com.layne.my.taobao.domain.TbContent;
import com.layne.my.taobao.domain.TbContentCategory;
import com.layne.my.taobao.web.api.dao.TbContentDao;
import com.layne.my.taobao.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectByCategoryId(Long categoryId) {
        TbContentCategory tbContentCategory=new TbContentCategory();
        tbContentCategory.setId(categoryId);
        TbContent tbContent=new TbContent();
        tbContent.setTbContentCategory(tbContentCategory);
        return tbContentDao.selectByCategoryId(tbContent);
    }
}
