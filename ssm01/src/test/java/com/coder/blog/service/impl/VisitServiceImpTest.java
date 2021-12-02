package com.coder.blog.service.impl;

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

    @Test
    public void deleteByPrimaryKey() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String each:beanDefinitionNames){
            System.out.println(each);
        }
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectVisitByIp() {
    }

    @Test
    public void findVisitTimes() {
    }

    @Test
    public void selectVisitListByDate() {
    }

    @Test
    public void selectLikeVisitListByPage() {
    }

    @Test
    public void selectVisitListByIp() {
    }

    @Test
    public void selectLikeVisitListGroupByIp() {
    }

    @Test
    public void getVisitDao() {
    }

    @Test
    public void setVisitDao() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void canEqual() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testToString() {
    }
}