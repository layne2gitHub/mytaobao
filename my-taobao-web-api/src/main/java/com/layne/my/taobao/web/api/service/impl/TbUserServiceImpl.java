package com.layne.my.taobao.web.api.service.impl;

import com.layne.my.taobao.domain.TbUser;
import com.layne.my.taobao.web.api.dao.TbUserDao;
import com.layne.my.taobao.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public TbUser login(TbUser tbUser) {
        TbUser loginTbUser = tbUserDao.login(tbUser);
        if(loginTbUser!=null){
            String md5PassWord = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if(md5PassWord.equals(loginTbUser.getPassword())){
                return loginTbUser;
            }
        }
        return null;
    }
}
