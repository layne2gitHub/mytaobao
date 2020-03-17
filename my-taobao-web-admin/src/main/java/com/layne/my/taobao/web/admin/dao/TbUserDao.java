package com.layne.my.taobao.web.admin.dao;

import com.layne.my.taobao.commons.persistnce.BaseDao;
import com.layne.my.taobao.domain.TbUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TbUserDao  extends BaseDao<TbUser> {

    /**
     * 用户登录
     * @param email
     * @return
     */
     TbUser getByEmail(String email);

}
