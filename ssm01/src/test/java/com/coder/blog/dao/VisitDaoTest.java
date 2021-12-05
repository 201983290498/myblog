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

  @Test
  public void deleteByPrimaryKey() {
  }

  @Test
  public void insert() {
  }

  @Test
  public void selectByPrimaryKey() {
  }

  @Test
  public void selectVisitByIp() {
    PageInfo<Visit> visits = visitservice.selectVisitPageByIp("192.168.46.1", 1, 5);
    System.out.println(visits);
    System.out.println(visits.getList().get(0));
  }

  @Test
  public void selectVisitBySessionId() {
  }

  @Test
  public void findVisitTimes() {
  }

  @Test
  public void selectLikeVisitListByPage() {
  }

  @Test
  public void selectLikeVisitListGroupByIp() {
  }

  @Test
  public void selectVisitListByDate() {
  }

  @Test
  public void selectVisitListByIp() {
  }

  @Test
  public void updateByPrimaryKeySelective() {
  }

  @Test
  public void updateByPrimaryKey() {
  }
}
