package com.coder.commonBase.service;

import com.coder.commonBase.entity.visit.VisitRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
