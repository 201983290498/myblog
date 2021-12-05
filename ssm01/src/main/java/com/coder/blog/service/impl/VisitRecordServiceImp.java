package com.coder.blog.service.impl;

import com.coder.blog.dao.VisitRecorderDao;
import com.coder.blog.entity.visit.VisitRecord;
import com.coder.blog.service.VisitRecordService;
import org.springframework.stereotype.Service;

/**
 * @Author coder
 * @Date 2021/12/4 19:26
 * @Description
 */
@Service
public class VisitRecordServiceImp implements VisitRecordService {

  private final VisitRecorderDao recorderDao;

  public VisitRecordServiceImp(VisitRecorderDao recorderDao) {
    this.recorderDao = recorderDao;
  }

  @Override
  public void insert(VisitRecord visitRecord) {
    recorderDao.insert(visitRecord);
  }
}
