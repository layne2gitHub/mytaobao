package com.layne.my.taobao.web.admin.web.controller;

import com.layne.my.taobao.commons.dto.BaseResult;
import com.layne.my.taobao.domain.TbContent;
import com.layne.my.taobao.web.admin.abstracts.AbstractBaseController;
import com.layne.my.taobao.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "content")
public class ContentController extends AbstractBaseController<TbContent,TbContentService> {

    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent=null;
        if(id != null){
            tbContent=service.getById(id);
        }else{
            tbContent=new TbContent();
        }
        return tbContent;
    }


    @Override
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        /* 由于使用的dataTable插件显示数据分页显示，不在从这里查询数据，由Ajax实现了数据查询
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers",tbUsers);*/
        return "content_list";
    }

    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public  String form(){
        return "content_form";
    }

    /**
     *
     * @param tbContent
     * @param redirectAttributes
     * @return
     *
     *
     * 关于redirectAttributes的说明：
     * 在修改或新增用户时，在操作成功时页面要跳转到成功views并且需要返回提示，
     * 由于这里需要使用重定向，HttpServletRequest被重置，所以并能存值，
     * 这里可使用Spring封装的RedirectAttributes来实现。
     */
    @Override
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save( TbContent tbContent, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbContent);
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }else{
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }

    }

    /**
     * 删除用户信息
     * @param ids
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "deleteMulti",method = RequestMethod.POST)
    public BaseResult deleteMulti(String ids){
        BaseResult baseResult=null;
        if(!StringUtils.isBlank(ids)){
            String[] idArray=ids.split(",");
            service.deleteMulti(idArray);
            baseResult= BaseResult.success("删除内容信息成功");
        }else{
            baseResult= BaseResult.fail("提交内容信息失败");
        }
        return baseResult;
    }

    /**
     * 显示用户详情
     * @return
     */
    @Override
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(){
        return "content_detail";
    }

}
