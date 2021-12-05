package com.coder.blog.dao;


import com.coder.blog.entity.visit.Visit;
import com.coder.blog.entity.visit.VisitRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * The interface Visit recorder dao.
 *
 * @Author coder
 * @Date 2021 /12/4 19:00
 * @Description 开启缓存, 访客记录登记 。
 */
@CacheNamespace(blocking = true)
public interface VisitRecorderDao {

  /**
   * 插入访问记录
   *
   * @param record 插入的访问记录
   */
  @Insert("insert into visit_record(username,ip,url,applicationType,resourceType,message,visit_time,sessionId)" +
    "values(#{username,jdbcType=VARCHAR},#{ip},#{url},#{applicationType},#{resourceType},#{message,jdbcType=VARCHAR},#{time},#{sessionId,jdbcType=VARCHAR})")
  void insert(VisitRecord record);


  /**
   * 按照sessionId查询访问记录
   *
   * @param sessionId 通过sessionID查看访问的情况
   * @return 返回值为访问记录 list
   */
  @Results(id="visit_record",value = {
    @Result(property="time",column = "visit_time"),
    @Result(property = "visit",column = "sessionId", javaType = Visit.class,one = @One(select="com.coder.blog.dao.VisitDao.selectVisitBySessionId",fetchType= FetchType.LAZY))
  })
  @Select("select * from visit_record where sessionId=#{arg0}")
  List<VisitRecord> selectListPageBySessionId(String sessionId);

  /**
   * 通过userName查询访问记录
   *
   * @param username username
   * @return 访问记录 list
   */
  @ResultMap(value="visit_record")
  @Select("select * from visit_record where username=#{username}")
  List<VisitRecord> selectListPageByUsername(String username);;


  /**
   * 查询所有的访问资源记录
   *
   * @return list
   */
  @ResultMap(value="visit_record")
  @Select("select * from visit_record")
  List<VisitRecord> selectAll();
}
