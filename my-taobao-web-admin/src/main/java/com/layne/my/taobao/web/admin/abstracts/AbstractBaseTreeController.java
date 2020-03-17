package com.layne.my.taobao.web.admin.abstracts;

import com.layne.my.taobao.commons.dto.BaseResult;
import com.layne.my.taobao.commons.dto.PageInfo;
import com.layne.my.taobao.commons.persistnce.BaseTreeEntity;
import com.layne.my.taobao.commons.persistnce.BaseTreeService;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class AbstractBaseTreeController<T extends BaseTreeEntity,S extends BaseTreeService<T>> extends AbstractBaseController<T,S> {

    public abstract String list(Model model);

    @Override
    public String list(){
        return null;
    };

    @Override
    public PageInfo page(HttpServletRequest request, T entity) {
        return null;
    }

    public abstract List<T> treeData(Long id);

    /**
     * 跳转新增表单页
     * @return
     */
    public abstract String form(T entity);

    @Override
    public String form(){
        return null;
    };

    /**
     * 跳转到详情页
     * @return
     */
    @Override
    public  String detail(){
        return null;
    };

    public  BaseResult deleteMulti(String ids){
        return null;
    };

    /**
     * 实现jQueryTree,对数据进行排序
     * @param sourceList 源数据
     * @param targetList 排序后数据
     * @param parentId 父节点ID
     */
    public void sortList(List<T> sourceList, List<T> targetList, Long parentId){
        for(T sourceEntity:sourceList){
            if(sourceEntity.getParent().getId().equals(parentId)){
                targetList.add(sourceEntity);
                //判断有没有子节点，如果有继续追加
                if(sourceEntity.getIsParent()){
                    for(T currentEntity:sourceList){
                        if(currentEntity.getParent().getId().equals(sourceEntity.getId())){
                            sortList(sourceList,targetList,sourceEntity.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
}
