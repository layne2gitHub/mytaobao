package com.layne.my.taobao.web.admin.web.controller;

import com.layne.my.taobao.commons.dto.BaseResult;
import com.layne.my.taobao.commons.dto.PageInfo;
import com.layne.my.taobao.domain.TbUser;
import com.layne.my.taobao.web.admin.abstracts.AbstractBaseController;
import com.layne.my.taobao.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
/**
 * 用户管理
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractBaseController<TbUser,TbUserService> {

    @Autowired
    private TbUserService tbUserService;

    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser=null;
        if(id != null){
            tbUser=tbUserService.getById(id);
        }else{
            tbUser=new TbUser();
        }
        return tbUser;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        /* 由于使用的dataTable插件显示数据分页显示，不在从这里查询数据，由Ajax实现了数据查询
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers",tbUsers);*/
        return "user_list";
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public  String form(){
        return "user_form";
    }

    /**
     *
     * @param tbUser
     * @param redirectAttributes
     * @return
     *
     *
     * 关于redirectAttributes的说明：
     * 在修改或新增用户时，在操作成功时页面要跳转到成功views并且需要返回提示，
     * 由于这里需要使用重定向，HttpServletRequest被重置，所以并能存值，
     * 这里可使用Spring封装的RedirectAttributes来实现。
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model,RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbUserService.save(tbUser);
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }else{
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }

    }

    @Override
    public BaseResult deleteMulti(String ids) {
        return null;
    }

    /**
     * 删除用户信息
     *
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult=null;
        if(!StringUtils.isBlank(ids)){
            String[] idArray=ids.split(",");
            service.deleteMulti(idArray);
            baseResult= BaseResult.success("删除用户成功");
        }else{
            baseResult= BaseResult.fail("提交用户失败");
        }
        return baseResult;
    }


   /* @ResponseBody
    @RequestMapping(value = "page",method =RequestMethod.GET)
    public String page(HttpServletRequest request){
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            //显示request请求里的数值
            System.out.println(String.format("key:%s   value:%s",parameterNames.nextElement(),request.getParameter(parameterNames.nextElement())));
        }
        return null;
    }

    key:draw   value:0
    key:columns[0][name]   value:true
    key:columns[0][orderable]   value:
    key:columns[0][search][regex]   value:1
    key:columns[1][name]   value:true
    key:columns[1][orderable]   value:
    key:columns[1][search][regex]   value:2
    key:columns[2][name]   value:true
    key:columns[2][orderable]   value:
    key:columns[2][search][regex]   value:3
    key:columns[3][name]   value:true
    key:columns[3][orderable]   value:
    key:columns[3][search][regex]   value:4
    key:columns[4][name]   value:true
    key:columns[4][orderable]   value:
    key:columns[4][search][regex]   value:5
    key:columns[5][name]   value:true
    key:columns[5][orderable]   value:
    key:columns[5][search][regex]   value:6
    key:columns[6][name]   value:true
    key:columns[6][orderable]   value:
    key:columns[6][search][regex]   value:0
    key:length   value:
    key:search[regex]   value:1578552899476

    */
    /**
     * 显示用户详情
     * @return
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(){
        return "user_detail";
    }
}







































