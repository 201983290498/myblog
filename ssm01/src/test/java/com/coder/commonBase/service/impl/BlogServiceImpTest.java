package com.coder.commonBase.service.impl;

import com.coder.commonBase.service.BlogService;
import com.coder.commom.annotation.Enum.BlogType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class BlogServiceImpTest {

  @Autowired
  private BlogService blogService;

  @Test
  public void selectCountNow() {
    System.out.println(blogService.selectCountNow());
  }

    @Test
    public void selectTypeCount() {
      Class aclass = BlogType.class;
      System.out.println(aclass.getFields().toString());
      for(Field field : aclass.getFields()){
        System.out.println(field.getName());
      }
    }

  @Test
  public void selectCountByCondition() {
    System.out.println(blogService.selectTypeCount(BlogType.class));
  }

  @Test
  public void selectCountByMonth() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    System.out.println(calendar.getTime());
    calendar.set(Calendar.DATE,1);
    System.out.println(calendar.get(Calendar.YEAR));
    System.out.println(calendar.get(Calendar.MONTH));
    System.out.println(calendar.get(Calendar.DATE));
    System.out.println(calendar.get(Calendar.HOUR));
    System.out.println(calendar.get(Calendar.MINUTE));
    System.out.println(calendar.get(Calendar.SECOND));
  }
}
