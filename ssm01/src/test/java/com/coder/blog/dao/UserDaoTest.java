package com.coder.blog.dao;

import com.coder.blog.entity.User;
import org.apache.shiro.config.Ini;
import org.apache.shiro.realm.AuthorizingRealm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class UserDaoTest {

    @Autowired
    public AuthorizingRealm myRealm;

    @Autowired
    public UserDao userDao;

    @Test
    public void selectOne(){
        Ini ini = Ini.fromResourcePath("classpath:shiro.ini");
        System.out.println(ini.toString());
        System.out.println(myRealm);
    }

    @Test
    public void testSelectOne() {
        User user = userDao.selectOne("cjm");
        if(user==null)
            System.out.println("null");
        else{
            System.out.println(user);
        }
    }

    @Test
    public void insert() {
        User user = new User("111", "baidu", "wea", "das", null, null, new Date(System.currentTimeMillis()));
        userDao.insert(user);
    }
}