package com.layne.my.taobao.web.api.service;

import com.layne.my.taobao.domain.TbContent;

import java.util.List;

public interface TbContentService {
    List<TbContent> selectByCategoryId(Long categoryId);
}
