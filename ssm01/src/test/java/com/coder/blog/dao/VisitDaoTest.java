package com.coder.blog.dao;

import com.coder.blog.entity.visit.Visit;
import com.coder.blog.service.VisitService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class VisitDaoTest {

  @Autowired
  private VisitService visitservice;

  @Autowired
  private VisitDao visitDao;

  @Test
  public void selectVisitByIp() {
    PageInfo<Visit> visits = visitservice.selectVisitPageByIp("192.168.46.1", 1, 5);
    System.out.println(visits);
    System.out.println(visits.getList().get(0));
  }
}
