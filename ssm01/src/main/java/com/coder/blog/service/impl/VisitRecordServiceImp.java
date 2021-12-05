package com.coder.blog.service.impl;

import com.coder.blog.dao.VisitRecorderDao;
import com.coder.blog.entity.visit.VisitRecord;
import com.coder.blog.service.VisitRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * The type Visit record service imp.
 *
 * @Author coder
 * @Date 2021 /12/4 19:26
 * @Description
 */
@Service
public class VisitRecordServiceImp implements VisitRecordService {

  private final VisitRecorderDao recorderDao;

    /**
     * Instantiates a new Visit record service imp.
     *
     * @param recorderDao the recorder dao
     */
    public VisitRecordServiceImp(VisitRecorderDao recorderDao) {
    this.recorderDao = recorderDao;
  }

  @Override
  public void insert(VisitRecord visitRecord) {
    recorderDao.insert(visitRecord);
  }

  @Override
  public PageInfo<VisitRecord> selectListPageBySessionId(String sessionId, int page, int size) {
    PageHelper.startPage(page,size);
    return new PageInfo<>(recorderDao.selectListPageBySessionId(sessionId));
  }

  @Override
  public PageInfo<VisitRecord> selectListByUsername(String username, int page, int size) {
    PageHelper.startPage(page,size);
    return new PageInfo<>(recorderDao.selectListPageByUsername(username));
  }

  @Override
  public PageInfo<VisitRecord> selectAllByPage(int page, int size) {
    PageHelper.startPage(page,size);
    return new PageInfo<>(recorderDao.selectAll());
  }
}
