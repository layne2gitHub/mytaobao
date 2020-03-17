package com.layne.my.taobao.web.ui.controller;

import com.layne.my.taobao.web.ui.api.ContentsApi;
import com.layne.my.taobao.web.ui.dto.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IndexController {
    /**
     * 跳转首页
     * @param model
     * @return
     */
    @RequestMapping(value = {"","index"},method = RequestMethod.GET)
    public String Index(Model model){
        requestContentPPT(model);
        return "index";
    }

    private void requestContentPPT(Model model){
        List<TbContent> ppt = ContentsApi.ppt();
        model.addAttribute("ppt",ppt);
    }
}
