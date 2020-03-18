package com.layne.my.taobao.commons.persistnce;

import java.util.List;

public interface BaseTreeService<T extends BaseEntity> extends Service<T>{


    /**
     * 根据父节点ID查询所有子节点
     * @param pId
     * @return
     */
    List<T> selectByPid(Long pId);
}
