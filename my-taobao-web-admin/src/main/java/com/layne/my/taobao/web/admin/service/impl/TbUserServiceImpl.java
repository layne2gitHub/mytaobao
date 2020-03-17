package com.layne.my.taobao.web.admin.service.impl;


import com.layne.my.taobao.commons.dto.BaseResult;
import com.layne.my.taobao.commons.dto.PageInfo;
import com.layne.my.taobao.commons.utils.RegexpUtils;
import com.layne.my.taobao.commons.validator.BeanValidator;
import com.layne.my.taobao.domain.TbUser;
import com.layne.my.taobao.web.admin.abstracts.AbstractBaseServiceImpl;
import com.layne.my.taobao.web.admin.dao.TbUserDao;
import com.layne.my.taobao.web.admin.service.TbUserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser,TbUserDao> implements TbUserService {


    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        if(validator!=null){
            return  BaseResult.fail(validator);
        }else{
            tbUser.setUpdated(new Date());
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            if(tbUser.getId() == null){
                tbUser.setCreated(new Date());
                dao.insert(tbUser);
            }else{
                update(tbUser);
            }
            return  BaseResult.success("保存用户信息成功");
        }
    }
    /*public BaseResult save(TbUser tbUser) {
        BaseResult baseResult=checkTbUser(tbUser);
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            tbUser.setUpdated(new Date());
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            if(tbUser.getId() == null){
                tbUser.setCreated(new Date());
                tbUserDao.insert(tbUser);
            }else{
                tbUserDao.update(tbUser);
            }
            baseResult.setMessage("保存用户信息成功");
        }
        return baseResult;
    }*/

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser=dao.getByEmail(email);
        if(tbUser!=null){
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            if(md5Password.equals(tbUser.getPassword())){
                return tbUser;
            }
        }
        return null;
    }

    /**
     * 用户信息的有效验证
     * @param tbUser
     * @return
     */
    private BaseResult checkTbUser(TbUser tbUser){
        BaseResult baseResult=BaseResult.success();
         if(StringUtils.isBlank(tbUser.getEmail())){
             baseResult= BaseResult.fail("邮箱不能为空，请重新输入");
         }else if(!RegexpUtils.checkEmail(tbUser.getEmail())){
             baseResult= BaseResult.fail("邮箱格式不正确，请重新输入");
         }else if(StringUtils.isBlank(tbUser.getPassword())){
             baseResult= BaseResult.fail("密码不能为空，请重新输入");
         }else if(StringUtils.isBlank(tbUser.getUsername())){
             baseResult= BaseResult.fail("姓名不能为空，请重新输入");
         }else if(StringUtils.isBlank(tbUser.getPhone())){
             baseResult= BaseResult.fail("手机号不能为空，请重新输入");
         }else  if(!RegexpUtils.checkPhone(tbUser.getPhone())){
             baseResult= BaseResult.fail("手机号格式不正确，请重新输入");
         }
         return baseResult;
    }
}
