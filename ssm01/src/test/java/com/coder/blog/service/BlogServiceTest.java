package com.coder.blog.service;

import com.coder.commom.annotation.Enum.BlogType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class BlogServiceTest {

  @Autowired
  private BlogService service;

  @Test
  public void selectTypeCount() {
    System.out.println(service.selectTypeCount(BlogType.class));
  }

  @Test
  public void getRecentFrequency() {
    System.out.println(service.getRecentFrequency(14));
  }
}
