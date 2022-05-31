package com.coder.commonBase.service.impl;

import com.coder.commonBase.Utils.TableFieldUtils;
import com.coder.commonBase.entity.User;
import com.coder.commonBase.service.UserService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class UserServiceImpTest {

  @Autowired
  private UserService service;
  @Test
  public void selectAllByPage() {
    PageInfo<User> userPageInfo = service.selectAllByPage(1, User.pageSize);
    Map<String, Object> userMap = new HashMap<>();
    TableFieldUtils.renderTable(userMap,userPageInfo,User.class);
    System.out.println(1);
  }
}
