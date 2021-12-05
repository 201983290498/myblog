package com.coder.blog.service;

import com.coder.blog.entity.visit.VisitRecord;
import com.github.pagehelper.PageInfo;

/**
 * @Author coder
 * @Date 2021/12/4 19:26
 * @Description
 */
public interface VisitRecordService {

  /**
   * 插入资源访问记录
   * @param visitRecord 访问记录
   */
  void insert(VisitRecord visitRecord);

  /**
   * 分页查询同一个SessionId的访问资源记录
   * @param sessionId sessionId
   * @param page 页数
   * @param size 每一页的大小
   * @return 返回页面信息
   */
  PageInfo<VisitRecord> selectListPageBySessionId(String sessionId, int page, int size);

  /**
   * 按照username进行分页查询
   * @param username
   * @param page
   * @param size
   * @return
   */
  PageInfo<VisitRecord> selectListByUsername(String username,int page,int size);

  /**
   * 分页查询所有的页面
   * @param page 页码
   * @param size 大小
   * @return
   */
  PageInfo<VisitRecord> selectAllByPage(int page,int size);
}
