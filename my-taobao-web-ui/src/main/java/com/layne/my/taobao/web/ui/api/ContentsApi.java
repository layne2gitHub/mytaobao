package com.layne.my.taobao.web.ui.api;

import com.layne.my.taobao.commons.utils.HttpClientUtils;
import com.layne.my.taobao.commons.utils.MapperUtils;
import com.layne.my.taobao.web.ui.dto.TbContent;

import java.util.List;

/**
 * 内容管理接口
 */
public class ContentsApi {


    public static List<TbContent> ppt() {
        String result = HttpClientUtils.doGet(API.API_CONTENTS+103);
        List<TbContent> tbContents=null;
        try {
            tbContents = MapperUtils.json2listByTree(result, "data", TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }
}
