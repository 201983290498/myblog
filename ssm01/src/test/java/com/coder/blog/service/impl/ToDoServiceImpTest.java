package com.coder.blog.service.impl;


import com.coder.blog.dao.ToDoDao;
import com.coder.blog.entity.ToDo;
import com.coder.blog.service.ToDoService;
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
public class ToDoServiceImpTest {

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private ToDoDao toDoDao;

    @Test
    public void selectList() {
      Map<String,Object> conditions = new HashMap<>();
      conditions.put("owner", "Coder1");
      conditions.put("status",0);
      conditions.put("order","finishTime");
      System.out.println(ToDo.pageSize);
//      System.out.println(toDoDao.selectList(null));
      PageInfo<ToDo> toDoPageInfo = toDoService.selectList(conditions, 1, ToDo.pageSize);
      System.out.println(toDoPageInfo.getList());
      System.out.println("分钟".contains("分"));
    }
}
