package com.layne.my.taobao.web.ui.api;

import com.layne.my.taobao.commons.dto.BaseResult;
import com.layne.my.taobao.commons.utils.HttpClientUtils;
import com.layne.my.taobao.commons.utils.MapperUtils;
import com.layne.my.taobao.web.ui.dto.TbUser;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员管理接口
 */
public class UsersApi {
    /**
     * 登录
     * @param tbUser
     * @return
     */
    public static TbUser login(TbUser tbUser) throws Exception {
        List<BasicNameValuePair> params=new ArrayList<>();
        params.add(new BasicNameValuePair("username",tbUser.getUsername()));
        params.add(new BasicNameValuePair("password",tbUser.getPassword()));
        //list集合转数组，新建一个数组指定大小
        String json = HttpClientUtils.doPost(API.API_USERS_LOGIN,params.toArray(new BasicNameValuePair[params.size()]));
        TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);
        return user;
    }
}
