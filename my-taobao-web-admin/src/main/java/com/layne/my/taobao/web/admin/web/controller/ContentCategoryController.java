package com.layne.my.taobao.web.admin.web.controller;

import com.layne.my.taobao.commons.dto.BaseResult;
import com.layne.my.taobao.domain.TbContentCategory;
import com.layne.my.taobao.web.admin.abstracts.AbstractBaseTreeController;
import com.layne.my.taobao.web.admin.service.TbContentCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容分类管理
 */
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController extends AbstractBaseTreeController<TbContentCategory,TbContentCategoryService> {

    @ModelAttribute
    public TbContentCategory getTbContentCategory(Long id){
        TbContentCategory tbContentCategory=null;
        if(id != null){
            tbContentCategory= service.getById(id);
        }else{
            tbContentCategory=new TbContentCategory();
        }
        return tbContentCategory;
    }
    @Override
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model) {
        List<TbContentCategory> tbContentCategories = service.selectAll();
        List<TbContentCategory> targetList = new ArrayList<>();
        sortList(tbContentCategories,targetList,0L);
        model.addAttribute("tbContentCategories",targetList);
        return "content_category_list";
    }

    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public  String form(TbContentCategory tbContentCategory){
        return "content_category_form";
    }
    /**
     * 树形内容类型数据
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "tree/data",method =RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id){
        if(id==null){
            id=0L;
        }
        return service.selectByPid(id);
    }
    @Override
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(tbContentCategory);
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/category/list";
        }else{
            model.addAttribute("baseResult",baseResult);
            return "content_category_form";
        }
    }

    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(Long id){
        BaseResult baseResult=null;
        if(id!=null){
            service.delete(id);
            baseResult= BaseResult.success("删除成功");
        }else{
            baseResult= BaseResult.fail("删除失败");
        }
        return baseResult;
    }
}
