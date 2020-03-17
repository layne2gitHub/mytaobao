package com.layne.my.taobao.web.admin.service.impl;

import com.layne.my.taobao.commons.dto.BaseResult;
import com.layne.my.taobao.commons.dto.PageInfo;
import com.layne.my.taobao.commons.validator.BeanValidator;
import com.layne.my.taobao.domain.TbContent;
import com.layne.my.taobao.web.admin.abstracts.AbstractBaseServiceImpl;
import com.layne.my.taobao.web.admin.dao.TbContentDao;
import com.layne.my.taobao.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent,TbContentDao> implements TbContentService {

    /*@Override
    public List<TbContent> selectAll() {
        return tbContentDao.selectAll();
    }*/

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);
        if(validator!=null){
            return  BaseResult.fail(validator);
        }else{
            tbContent.setUpdated(new Date());
            if(tbContent.getId() == null){
                tbContent.setCreated(new Date());
                dao.insert(tbContent);
            }else{
                update(tbContent);
            }
            return  BaseResult.success("保存内容信息成功");
        }
    }

    /*public BaseResult insert(TbContent tbContent) {
        BaseResult baseResult=checkTbUser(tbContent);
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            tbContent.setUpdated(new Date());
            if(tbContent.getCategoryId() == null){
                tbContent.setCreated(new Date());
                tbContentDao.insert(tbContent);
            }else{
                tbContentDao.update(tbContent);
            }
            baseResult.setMessage("保存内容信息成功");
        }
        return baseResult;
    }*/

   /* @Override
    public void delete(Long id) {
        tbContentDao.delete(id);
    }

    @Override
    public TbContent getById(Long id) {
        return tbContentDao.getById(id);
    }

    @Override
    public void update(TbContent tbContent) {
        tbContentDao.update(tbContent);
    }

    @Override
    public void deleteMulti(String[] ids) {
        tbContentDao.deleteMulti(ids);
    }*/

   /* @Override
    public PageInfo<TbContent> page(int start, int length,int draw,TbContent tbContent) {
        Map<String,Object> map=new HashMap<>();
        map.put("start",start);
        map.put("length",length);
        map.put("tbContent",tbContent);
        int countTotal = tbContentDao.countTotal(tbContent);
        PageInfo<TbContent> pageInfo=new PageInfo<TbContent>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(countTotal);
        pageInfo.setRecordsFiltered(countTotal);
        pageInfo.setData(tbContentDao.page(map));
        return pageInfo;
    }*/
   /* @Override
    public int countTotal(TbContent tbContent) {
        return tbContentDao.countTotal(tbContent);
    }*/
    /**
     * 用户信息的有效验证
     * @param tbContent
     * @return
     */
    private BaseResult checkTbUser(TbContent tbContent){
        BaseResult baseResult=BaseResult.success();
        if(StringUtils.isBlank(tbContent.getTitle())){
            baseResult= BaseResult.fail("标题不能为空，请重新输入");
        }else if(StringUtils.isBlank(tbContent.getSubTitle())){
            baseResult= BaseResult.fail("子标题不能为空，请重新输入");
        }else if(StringUtils.isBlank(tbContent.getTitleDesc())){
            baseResult= BaseResult.fail("标题描述不能为空，请重新输入");
        }
        return baseResult;
    }
}
