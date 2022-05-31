package com.coder.commonBase.service;

import com.coder.commonBase.entity.visit.VisitRecord;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * The interface Visit record service.
 *
 * @Author coder
 * @Date 2021 /12/4 19:26
 * @Description
 */
public interface VisitRecordService {

  /**
   * 插入资源访问记录
   *
   * @param visitRecord 访问记录
   */
  void insert(VisitRecord visitRecord);

  /**
   * 分页查询同一个SessionId的访问资源记录
   *
   * @param sessionId sessionId
   * @param page      页数
   * @param size      每一页的大小
   * @return 返回页面信息 page info
   */
  PageInfo<VisitRecord> selectListPageBySessionId(String sessionId, int page, int size);

  /**
   * 按照username进行分页查询
   *
   * @param username the username
   * @param page     the page
   * @param size     the size
   * @return page info
   */
  PageInfo<VisitRecord> selectListByUsername(String username,int page,int size);

  /**
   * 分页查询所有的页面
   *
   * @param page 页码
   * @param size 大小
   * @return page info
   */
  PageInfo<VisitRecord> selectAllByPage(int page,int size);
  List<VisitRecord> selectAll();
  /**
   * 获取最近一次的访问资源的频率
   * @return
   */
  Integer selectCountNow();

  /**
   * 获取访问资源的数量
   * @param map 条件
   * @return
   */
  Integer selectCount(Map<String,Object> map);

}
