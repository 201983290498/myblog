package com.coder.blog.service;

import com.coder.blog.entity.visit.Visit;

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

    Visit selectVisitByIp(String ip);

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
    List<?> selectVisitListByDate(Map<String, Object> map);

    /**
     * 模糊查询  and 分组显示
     * @param map
     * @return
     */
    List<?> selectLikeVisitListGroupByIp(Map<String, Object> map);


    List<Visit> selectLikeVisitListByPage(Map<String, Object> map);

    int updateByPrimaryKeySelective(Visit record);

    int updateByPrimaryKey(Visit record);

    /**
     *  根据IP分组查询
     * @return
     */
    List<?>  selectVisitListByIp(Map<String, Object> map);
}
