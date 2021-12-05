package com.coder.blog.service;

import com.coder.blog.entity.visit.Visit;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author coder
 * @Date 2021/11/26 23:10
 * @Description
 */
public interface VisitService {
    int deleteByPrimaryKey(Integer id);

    int insert(Visit record);

    Visit selectByPrimaryKey(Integer id);

  PageInfo<Visit> selectVisitPageByIp(String ip, Integer page, Integer pageSize);

    /**
     * @param visit 访问记录
     * @return
     * @Description
     */
    Long findVisitTimes(Visit visit);

    /**
     *  根据日期月份分组查询
     * @return
     */
    PageInfo<?> selectVisitListByDate(Map<String, Object> map,int page,int size);

    /**
     * 模糊查询  and 分组显示
     * @param map
     * @return
     */
    PageInfo<?> selectLikeVisitListGroupByIp(Map<String, Object> map,int page,int size);


    PageInfo<Visit> selectLikeVisitListByPage(Map<String, Object> map,int page,int size);

  /**
   * 查询所有的记录
   * @param page
   * @param size
   * @return
   */
  PageInfo<Visit> selectAllByPage(int page,int size);

    int updateByPrimaryKeySelective(Visit record);

    int updateByPrimaryKey(Visit record);

    /**
     *  根据IP分组查询
     * @return
     */
    PageInfo<?>  selectVisitListByIp(Map<String, Object> map,int page,int size);
}
