package com.coder.blog.dao;

import com.coder.blog.entity.visit.Visit;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


/**
 * @author coder
 */
@CacheNamespace(blocking = true)
public interface VisitDao {


    @Select("select id, ip,userAgent, city,url,browserType,platformType, time,sessionId from visit where id=#{param1}")
    int deleteByPrimaryKey(Integer id);

    @Insert("insert into visit(ip,userAgent, city,url,browserType,platformType, time,sessionId) values (#{ip},#{userAgent},#{city},#{url},#{browserType},#{platformType},#{time},#{sessionId})" )
    int insert(Visit record);

    @Select("select id, ip,userAgent, city,url,browserType,platformType, time, sessionId from visit where id=#{id}")
    Visit selectByPrimaryKey(Integer id);

    @Select("select id, ip,userAgent, city,url,browserType,platformType, time, sessionId form visit where ip=#{ip}")
    Visit selectVisitByIp(String ip);

    @Select("select id, ip,userAgent, city,url,browserType,platformType, time, sessionId form visit where sessionId=#{sessionId}")
    Visit selectVisitBySessionId(String sessionId);

    Long findVisitTimes(Visit visit);

    /**
     * 模糊查询
     * @param map
     * @return
     */
    List<Visit> selectLikeVisitListByPage(Map<String, Object> map);


    /**
     * 模糊查询  and 分组显示
     * @param map
     * @return
     */
    List<?> selectLikeVisitListGroupByIp(Map<String, Object> map);

    /**
     *  根据日期月份分组查询
     * @return
     */
    List<?>  selectVisitListByDate(Map<String, Object> map);


    /**
     *  根据IP分组查询
     * @return 返回一个列表
     */
    @Select("select count(*) as count from visit group by ip,userAgent order by count desc")
    List<?>  selectVisitListByIp(Map<String, Object> map);


    int updateByPrimaryKeySelective(Visit record);

    @Update("update visit set ip=#{ip},userAgent=#{userAgent},city=#{city},url=#{url},browserType=#{browserType},platformType=#{platformType},time=#{time},sessionId=#{sessionId},where id=#{id}")
    int updateByPrimaryKey(Visit record);
}
