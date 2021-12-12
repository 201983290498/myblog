package com.coder.blog.dao;

import com.coder.blog.entity.BlogType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class BlogTypeDaoTest {

  @Autowired
  private BlogTypeDao blogTypeDao;

  @Test
  public void selectTypeCount() {
  }

  @Test
  public void insert() {
    BlogType blogType = new BlogType();
    blogType.setAddTime(new Date());
    blogType.setTypename(com.coder.commom.annotation.Enum.BlogType.Others);
    blogTypeDao.insert(blogType);
    System.out.println(blogType);
  }

  @Test
  public void selectOneBlogType() {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("id",1);
    BlogType blogType = blogTypeDao.selectOneBlogType(map);
    System.out.println(blogType);
    com.coder.commom.annotation.Enum.BlogType dairy = com.coder.commom.annotation.Enum.BlogType.getBlogType("da");
    System.out.println(dairy);
    System.out.println(blogType.getTypename());
  }

  @Test
  public void updateBlogType() {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("id",1);
    BlogType blogType = blogTypeDao.selectOneBlogType(map);
    blogType.setNum(blogType.getNum()-1);
    blogTypeDao.updateBlogType(blogType);
  }

  @Test
  public void testSelectTypeCount() {
    Class aclass = com.coder.commom.annotation.Enum.BlogType.class;
    Field[] fields = aclass.getFields();
    for(int i=0;i<fields.length;i++) {
      String name = fields[i].getName();
      BlogType blogType = blogTypeDao.selectTypeCount(name);
      System.out.println(blogType);
    }
  }
}
