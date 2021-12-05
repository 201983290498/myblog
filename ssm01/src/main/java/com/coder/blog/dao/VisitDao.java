package com.coder.blog.dao;

import com.coder.blog.entity.visit.Visit;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @author coder
 */
@CacheNamespace(blocking = true)
public interface VisitDao {


   /**
    *  方法用来删除记录
    * @param id 要删除的记录Id
    * @return 是否删除成功
   */
    @Delete("delete from visit where id=#{param1}")
    int deleteByPrimaryKey(Integer id);

    /**
    * 用来插入数据
    * @param record 访问记录
    * @return 插入后影响的条数
    */
    @Insert("insert into visit(ip,userAgent, city,url,browserType,platformType, time,sessionId) values (#{ip},#{userAgent},#{city},#{url},#{browserType},#{platformType},#{time},#{sessionId})" )
    int insert(Visit record);

    /**
    * 通过id查找记录
    * @param id 查找的id
    * @return 返回记录
    */
    @Select("select id, ip,userAgent, city,url,browserType,platformType, time, sessionId from visit where id=#{id}")
    Visit selectByPrimaryKey(Integer id);

    /**
    * 根据分页查找同一个ip的记录
    * @param ip 查询的ip地址
    * @return
    */
    @Select("select id, ip,userAgent, city,url,browserType,platformType, time, sessionId from visit where ip=#{ip}")
    List<Visit> selectVisitPageByIp(@Param("ip") String ip);

    /**
     *  session的查找结果只会有一条,可以用来在查看记录的时候反向搜索对应的记录
     * @param sessionId sessionId
     * @return
     */
    @Select("select id, ip,userAgent, city,url,browserType,platformType, time, sessionId from visit where sessionId=#{sessionId}")
    Visit selectVisitBySessionId(@Param("sessionId") String sessionId);

  /**
   * 查询所有的记录
   * @return
   */
  @Select("select id, ip,userAgent, city,url,browserType,platformType, time, sessionId from visit)")
    List<Visit> selectALl();
  /**
     * 看某条记录是否记录过
     * @param visit 访问记录
     * @return  返回同一个session是否已经记录过它的访问
     */
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
   * 按日期查询访问的大致记录
   * @param map 查询参数
   * @return
   */
    List<?>  selectVisitListByDate(Map<String, Object> map);


    /**
     *  根据IP分组查询
     * @return 返回一个列表
     */
    @Select("select count(*) as count from visit group by ip,userAgent order by count desc")
    List<?>  selectVisitListByIp(Map<String, Object> map);

  /**
   * 更新某条记录，根据id
   * @param record 访问记录
   * @return
   */
    int updateByPrimaryKeySelective(Visit record);

  /**
   * 更新某条记录，根据id
   * @param record 访问记录
   * @return
   */
    @Update("update visit set ip=#{ip},userAgent=#{userAgent},city=#{city},url=#{url},browserType=#{browserType},platformType=#{platformType},time=#{time},sessionId=#{sessionId},where id=#{id}")
    int updateByPrimaryKey(Visit record);
}
