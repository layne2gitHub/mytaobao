package com.layne.my.taobao.web.ui.controller;

import com.google.code.kaptcha.Constants;
import com.layne.my.taobao.commons.dto.BaseResult;
import com.layne.my.taobao.commons.utils.EmailSendUtils;
import com.layne.my.taobao.web.ui.api.UsersApi;
import com.layne.my.taobao.web.ui.constant.SystemConstant;
import com.layne.my.taobao.web.ui.dto.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private EmailSendUtils emailSendUtils;

    @RequestMapping(value ="login",method = RequestMethod.GET)
    public String Index(){
        return "login";
    }

    @RequestMapping(value ="login",method = RequestMethod.POST)
    public String Index(TbUser tbUser, HttpServletRequest request, Model model) throws Exception {
        //验证验证码
        if(!checkVerification(tbUser, request)){
            model.addAttribute("baseResult",BaseResult.fail("验证码错误，请重新输入"));
            return "login";
        }
        TbUser user = UsersApi.login(tbUser);
        if(user==null){
            model.addAttribute("baseResult",BaseResult.fail("用户名或密码错误，请重新输入"));
            return "login";
        }else {
            //面向切面
            //登陆成功后发送邮件
            emailSendUtils.send("用户登录",String.format("用户【%s】登录MyTaobao",user.getUsername()),"layne@163.com");
            //将会员信息存入session中
            request.getSession().setAttribute(SystemConstant.SESSION_USER_KEY,user);
            return "redirect:/index";
        }
    }

    /**
     * 注销
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return "redirect:/login";
    }

    /**
     * 验证验证码
     * @param tbUser
     * @param httpServletRequest
     * @return
     */
    private boolean checkVerification(TbUser tbUser,HttpServletRequest httpServletRequest){
        String verification = (String) httpServletRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //if(verification.equals(tbUser.getVerification())){
        if(StringUtils.equals(verification,tbUser.getVerification())){
            return true;
        }
        return false;
    }
}
