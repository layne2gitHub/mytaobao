package com.layne.my.taobao.web.admin.service.test;

import com.layne.my.taobao.domain.TbUser;
import com.layne.my.taobao.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll() {
        List<TbUser> tbUsers = tbUserService.selectAll();
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public  void testInsert(){
        TbUser tbUser=new TbUser();
        tbUser.setUsername("layne");
        tbUser.setPhone("1585858585");
        tbUser.setEmail("layne@layne.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setUpdated(new Date());
        tbUser.setCreated(new Date());
        tbUserService.save(tbUser);
    }
    @Test
    public void testDelete(){
        tbUserService.delete(38L);
    };

    @Test
    public void testGetById(){
        TbUser tbUser = tbUserService.getById(36L);
        System.out.println(tbUser.getUsername());
    };

    @Test
    public void testUpdate(){
        TbUser tbUser = tbUserService.getById(36L);
        tbUser.setUsername("layne");
        tbUserService.update(tbUser);
    };

    @Test
    public void testMd5(){
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    };
}
