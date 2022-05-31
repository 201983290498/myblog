package com.coder.commonBase.dao;

import com.coder.commonBase.entity.visit.VisitRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class VisitRecorderDaoTest {

  @Autowired
  private VisitRecorderDao dao;

  @Test
  public void insert() {
  }

  @Test
  public void selectRecordsBySessionId() {
    List<VisitRecord> visitRecords = dao.selectListPageBySessionId("48b3c994-c10c-43b7-ad6a-1ce303719d5a");
    System.out.println(visitRecords);
  }

  @Test
  public void selectRecordsByUsername() {
  }
}
