package com.layne.my.taobao.web.admin.web.controller;

import com.layne.my.taobao.commons.constant.ConstantUtils;
import com.layne.my.taobao.domain.TbUser;
import com.layne.my.taobao.web.admin.service.TbUserService;
import com.layne.my.taobao.web.admin.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private TbUserService  tbUserService;
    /**
     * 跳转登录页面
     * @return
     */
    //@RequestMapping(value ="login",method = RequestMethod.GET)
    @RequestMapping(value ={"","login"},method = RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest, Model model){
        String userInfo  = CookieUtils.getCookieValue(httpServletRequest, ConstantUtils.SESSION_USER);
        if(!StringUtils.isBlank(userInfo)){
            String[] userInfoArray = userInfo .split(":");
            String email = userInfoArray[0];
            String password = userInfoArray[1];
            model.addAttribute("email",email);
            model.addAttribute("password",password);
            model.addAttribute("isRemember",true);
        }
        return "login";
    }

    /**
     * 登录逻辑
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password, String isRemember, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Model model){
        TbUser tbUser = tbUserService.login(email, password);
        boolean isRememberCode=(isRemember==null?false:true);
        if(tbUser!=null){
            if(isRememberCode){
                CookieUtils.setCookie(httpServletRequest,httpServletResponse,ConstantUtils.SESSION_USER,String.format("%s:%s",email,password),24*60*60*7);
            }else{
                CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,ConstantUtils.SESSION_USER);
            }
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER,tbUser);
            return "redirect:/main";
        }else{
            //httpServletRequest.setAttribute("message","账户或密码错误");
            model.addAttribute("message","账户或密码错误");
            return login(httpServletRequest,model);
        }
    }

    /**
     * 注销
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public  String logout(HttpServletRequest httpServletRequest,Model model){
        httpServletRequest.getSession().invalidate();
        return  login(httpServletRequest,model);
    }

}