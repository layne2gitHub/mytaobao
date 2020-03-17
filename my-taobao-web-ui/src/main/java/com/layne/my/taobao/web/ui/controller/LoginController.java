package com.layne.my.taobao.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @RequestMapping(value ="login",method = RequestMethod.GET)
    public String Index(){
        return "login";
    }
}
