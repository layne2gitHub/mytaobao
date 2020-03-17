package com.layne.my.taobao.web.admin.abstracts;

import com.layne.my.taobao.commons.dto.BaseResult;
import com.layne.my.taobao.commons.dto.PageInfo;
import com.layne.my.taobao.commons.persistnce.BaseEntity;
import com.layne.my.taobao.commons.persistnce.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBaseController<T extends BaseEntity,S extends Service<T>> {

    @Autowired
    protected S service;

    /**
     * 跳转列表页
     */
    public abstract String list();

    /**
     * 跳转新增表单页
     * @return
     */
    public abstract String form();

    /**
     * 新增保存
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */

    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public abstract BaseResult deleteMulti(String ids);

    /**
     * 分页查询
     * @param request
     * @param entity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public  PageInfo<T> page(HttpServletRequest request, T entity){
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw==null?0:Integer.parseInt(strDraw);
        int start = strStart==null?0:Integer.parseInt(strStart);
        int length = strLength==null?0:Integer.parseInt(strLength);

        //封装Datatables需要的结果
        PageInfo<T> pageInfo = service.page(start,length,draw,entity);

        return pageInfo;
    };

    /**
     * 跳转到详情页
     * @return
     */
    public abstract String detail( );

}
