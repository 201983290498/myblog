package com.coder.blog.service;

import com.coder.blog.entity.visit.VisitRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class VisitRecordServiceTest {

  @Autowired
  private VisitRecordService service;
  @Test
  public void selectAll() {
    List<VisitRecord> visitRecords = service.selectAll();
    System.out.println(visitRecords);
  }
}
