package com.layne.my.taobao.web.ui.interceptor;

import com.layne.my.taobao.web.ui.constant.SystemConstant;
import com.layne.my.taobao.web.ui.dto.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInteceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        TbUser tbUser = (TbUser) httpServletRequest.getSession().getAttribute(SystemConstant.SESSION_USER_KEY);
        //未登录状态，放行
        if(tbUser==null){
            return true;
        }else{
            //登录状态，重定向去首页
            httpServletResponse.sendRedirect("/index");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
