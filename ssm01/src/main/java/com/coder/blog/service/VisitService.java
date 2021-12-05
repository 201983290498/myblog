package com.coder.blog.service;

import com.coder.blog.entity.visit.Visit;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * The interface Visit service.
 *
 * @Author coder
 * @Date 2021 /11/26 23:10
 * @Description
 */
public interface VisitService {
  /**
   * Delete by primary key int.
   *
   * @param id the id
   * @return the int
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * Insert int.
   *
   * @param record the record
   * @return the int
   */
  int insert(Visit record);

  /**
   * Select by primary key visit.
   *
   * @param id the id
   * @return the visit
   */
  Visit selectByPrimaryKey(Integer id);

  /**
   * Select visit page by ip page info.
   *
   * @param ip       the ip
   * @param page     the page
   * @param pageSize the page size
   * @return the page info
   */
  PageInfo<Visit> selectVisitPageByIp(String ip, Integer page, Integer pageSize);

  /**
   * Find visit times long.
   *
   * @param visit 访问记录
   * @return long
   * @Description
   */
  Long findVisitTimes(Visit visit);

  /**
   * 根据日期月份分组查询
   *
   * @param map  the map
   * @param page the page
   * @param size the size
   * @return page info
   */
  PageInfo<?> selectVisitListByDate(Map<String, Object> map,int page,int size);

  /**
   * 模糊查询  and 分组显示
   *
   * @param map  the map
   * @param page the page
   * @param size the size
   * @return page info
   */
  PageInfo<?> selectLikeVisitListGroupByIp(Map<String, Object> map,int page,int size);


  /**
   * Select like visit list by page page info.
   *
   * @param map  the map
   * @param page the page
   * @param size the size
   * @return the page info
   */
  PageInfo<Visit> selectLikeVisitListByPage(Map<String, Object> map,int page,int size);

  /**
   * 查询所有的记录
   *
   * @param page the page
   * @param size the size
   * @return page info
   */
  PageInfo<Visit> selectAllByPage(int page,int size);

  /**
   * Update by primary key selective int.
   *
   * @param record the record
   * @return the int
   */
  int updateByPrimaryKeySelective(Visit record);

  /**
   * Update by primary key int.
   *
   * @param record the record
   * @return the int
   */
  int updateByPrimaryKey(Visit record);

  /**
   * 根据IP分组查询
   *
   * @param map  the map
   * @param page the page
   * @param size the size
   * @return page info
   */
  PageInfo<?>  selectVisitListByIp(Map<String, Object> map,int page,int size);
}
