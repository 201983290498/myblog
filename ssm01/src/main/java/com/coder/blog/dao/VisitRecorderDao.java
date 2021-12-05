package com.coder.blog.dao;

import com.coder.blog.entity.visit.VisitRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author coder
 * @Date 2021/12/4 19:00
 * @Description 开启缓存,访客记录登记。
 */
@CacheNamespace(blocking = true)
public interface VisitRecorderDao {

  /**
   * @param record 插入的访问记录
   */
  @Insert("insert into visit_record(username,ip,url,applicationType,resourceType,message,visit_time,sessionId)" +
    "values(#{username,jdbcType=VARCHAR},#{ip},#{url},#{applicationType},#{resourceType},#{message,jdbcType=VARCHAR},#{time},#{sessionId,jdbcType=VARCHAR})")
  void insert(VisitRecord record);


  /**
   * @param sessionId 通过sessionID查看访问的情况
   * @return 返回值为访问记录
   */
  @Results(id="visit_record",value = {
    @Result(property="time",column = "visit_time")
  })
  @Select("select * from visit_record where sessionId=#{arg0}")
  List<VisitRecord> selectRecordsBySessionId(String sessionId);

  @ResultMap(value="visit_record")
  @Select("select * from visit_record where username=#{username}")
  List<VisitRecord> selectRecordsByUsername(String username);;


}
