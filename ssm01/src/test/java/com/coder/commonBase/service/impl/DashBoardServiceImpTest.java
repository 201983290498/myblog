package com.coder.commonBase.service.impl;

import com.coder.commonBase.Utils.RespMessageUtils;
import com.coder.commonBase.service.DashBoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class DashBoardServiceImpTest {

  @Autowired
  private DashBoardService service;

  @Test
  public void getDashBoardInfo() {
    Map<String, Object> coder1 = service.getDashBoardInfo("Coder1");
    System.out.println(coder1);
  }

  @Test
  public void recentFrequency() {
    System.out.println(RespMessageUtils.SUCCESS(service.recentFrequency(14)));
  }

  @Test
  public void getBlogCountByStatus() {
    Double t1;
    t1 = 1.0;
    System.out.println(t1);
  }
}
