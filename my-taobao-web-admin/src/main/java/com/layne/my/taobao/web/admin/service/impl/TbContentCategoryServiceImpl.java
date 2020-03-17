package com.layne.my.taobao.web.admin.service.impl;

import com.layne.my.taobao.commons.dto.BaseResult;
import com.layne.my.taobao.commons.validator.BeanValidator;
import com.layne.my.taobao.domain.TbContentCategory;
import com.layne.my.taobao.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.layne.my.taobao.web.admin.dao.TbContentCategoryDao;
import com.layne.my.taobao.web.admin.service.TbContentCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class TbContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<TbContentCategory,TbContentCategoryDao> implements TbContentCategoryService{


    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory entity) {
        String validator = BeanValidator.validator(entity);
        if(validator!=null){
            return  BaseResult.fail(validator);
        }else{
            entity.setUpdated(new Date());
            TbContentCategory parent = entity.getParent();
            if(parent==null || parent.getId()==null){
                parent.setId(0L);
                entity.setIsParent(true);
            }
            if(entity.getId()==null){
                entity.setCreated(new Date());
                entity.setIsParent(false);
                //父节点是否为根节点
                if(parent.getId()!=0L){
                    //查询当前节点的父级节点
                    TbContentCategory currentCategoryParent =getById(parent.getId());
                    //父节点不为根节点，将父节点修改为“父节点类型”
                    if(currentCategoryParent!=null){
                        currentCategoryParent.setIsParent(true);
                        update(currentCategoryParent);
                    }
                }else{
                    entity.setIsParent(true);
                }
                dao.insert(entity);
            }else{
                update(entity);
            }
            return BaseResult.success("保存内容信息成功");
        }
    }
}
