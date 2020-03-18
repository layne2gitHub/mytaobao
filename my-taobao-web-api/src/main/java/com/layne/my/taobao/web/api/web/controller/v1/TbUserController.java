package com.layne.my.taobao.web.api.web.controller.v1;

import com.layne.my.taobao.commons.dto.BaseResult;
import com.layne.my.taobao.domain.TbUser;
import com.layne.my.taobao.web.api.service.TbUserService;
import com.layne.my.taobao.web.api.web.dto.TbUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.path.v1}/users/")
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    private BaseResult login(TbUser tbUser){
        TbUserDTO tbUserDTO=null;
        TbUser user = tbUserService.login(tbUser);
        if(user!=null){
                tbUserDTO=new TbUserDTO();
                BeanUtils.copyProperties(user,tbUserDTO);
                return BaseResult.success("获取登录数据成功",tbUserDTO);
            }
        return BaseResult.fail("登录信息错误，请重新输入");
    }
}
