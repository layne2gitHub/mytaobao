package com.layne.my.taobao.web.api.dao;

import com.layne.my.taobao.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentDao {
    List<TbContent> selectByCategoryId(TbContent tbContent);
}
