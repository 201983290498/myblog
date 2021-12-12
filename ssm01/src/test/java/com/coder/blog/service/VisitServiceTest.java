package com.coder.blog.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class VisitServiceTest {

  @Autowired
  private VisitService service;

  @Test
  public void getRecentFrequency() {
    System.out.println(service.getRecentFrequency(14));
  }

  @Test
  public void selectCount() {
    Map<String, Object> map = new HashMap<>();
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.set(Calendar.HOUR,0);
    map.put("endTime",cal.getTime());
    cal.add(Calendar.DATE,-1);
    map.put("startTime",cal.getTime());
    System.out.println(service.selectCount(map));
  }
}
