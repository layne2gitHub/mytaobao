package com.layne.my.taobao.web.api.web.controller.v1;

import com.layne.my.taobao.commons.dto.BaseResult;
import com.layne.my.taobao.domain.TbContent;
import com.layne.my.taobao.web.api.service.TbContentService;
import com.layne.my.taobao.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "${api.path.v1}/contents")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @RequestMapping(value = "{category_id}",method = RequestMethod.GET)
    public BaseResult findContentByCategoryId(@PathVariable(value = "category_id")Long categoryId ){
        BaseResult baseResult=null;
        List<TbContentDTO> tbContentDTOS=null;
        List<TbContent> tbContents = tbContentService.selectByCategoryId(categoryId);
        if(tbContents != null && tbContents.size() >0 ){
            tbContentDTOS=new ArrayList<>();
            for(TbContent tbContent:tbContents){
                TbContentDTO tbContentDTO=new TbContentDTO();
                BeanUtils.copyProperties(tbContent,tbContentDTO);
                tbContentDTOS.add(tbContentDTO);
            }
            baseResult=BaseResult.success("获取传输对象成功",tbContentDTOS);
        }else {
            baseResult=BaseResult.fail("获取传输数据失败");
        }
        return baseResult;
    }
}
