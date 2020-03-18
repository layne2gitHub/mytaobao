package com.layne.my.taobao.web.api.dao;

import com.layne.my.taobao.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * 会员管理
 */
@Repository
public interface TbUserDao {
    /**
     * 登录查询数据
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}
