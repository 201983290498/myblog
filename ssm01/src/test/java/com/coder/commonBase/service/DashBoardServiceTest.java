package com.coder.commonBase.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class DashBoardServiceTest {

  @Autowired
  private DashBoardService service;

  @Test
  public void getDashBoardInfo() {
    Map<String,Object> data = new HashMap<>();
    System.out.println(service.getDashBoardInfo("Coder1"));
  }
}
