package com.coder.blog.service.impl;

import com.coder.blog.service.VisitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class VisitServiceImpTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private VisitService service;

    @Test
    public void deleteByPrimaryKey() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String each:beanDefinitionNames){
            System.out.println(each);
        }
    }

  @Test
  public void getRecentFrequency() {
    System.out.println(service.getRecentFrequency(14));
  }
}
