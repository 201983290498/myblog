package com.coder.commonBase.service.impl;

import com.coder.commonBase.service.VisitRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})

public class VisitRecordServiceImpTest {

  @Autowired
  private VisitRecordService service;
  @Test
  public void selectCountNow() {
    System.out.println(service.selectCountNow());
  }

  @Test
  public void selectCount() {
  }

    @Test
    public void testSelectCountNow() {

    }

  @Test
  public void testSelectCountNow1() {
  }

    @Test
    public void insert() {
    }

    @Test
    public void selectListPageBySessionId() {
    }

    @Test
    public void selectListByUsername() {
    }

    @Test
    public void selectAllByPage() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void testSelectCountNow2() {
    }

    @Test
    public void testSelectCount() {
    }
}
