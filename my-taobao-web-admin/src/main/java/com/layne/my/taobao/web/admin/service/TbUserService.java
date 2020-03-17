package com.layne.my.taobao.web.admin.service;

import com.layne.my.taobao.commons.dto.BaseResult;
import com.layne.my.taobao.commons.dto.PageInfo;
import com.layne.my.taobao.commons.persistnce.BaseService;
import com.layne.my.taobao.domain.TbUser;

import java.util.List;

public interface TbUserService extends BaseService<TbUser> {

 /**
  * 用户登录
  * @param email
  * @param password
  * @return
  */
 TbUser login(String email, String password);

}
