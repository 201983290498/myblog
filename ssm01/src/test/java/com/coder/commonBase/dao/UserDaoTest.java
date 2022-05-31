package com.coder.commonBase.dao;

import com.coder.commonBase.entity.User;
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
      System.out.println(userDao.selectOne("Coder1"));
    }



    @Test
    public void insert() {
        User user = new User("111", "baidu", "wea", "das", null, null, new Date(System.currentTimeMillis()));
        userDao.insert(user);
    }

    @Test
    public void selectCount() {
      System.out.println(userDao.selectCount());
    }
}
