package com.coder.blog.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class ToDoDaoTest {

  @Test
  public void selectOne() {
  }

  @Test
  public void selectList() {
  }

  @Test
  public void selectCount() {
  }

  @Test
  public void insert() {
  }
}
