package com.coder.commonBase.dao;

import com.coder.commonBase.entity.ToDo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Author coder
 * @Date 2021/12/11 22:34
 * @Description
 */
@CacheNamespace(blocking = true)
public interface ToDoDao {


  /**
   * 代做事项列表
   * @param id 代做事项的id
   * @return
   */
  @Results(id = "todo",value = {
    @Result(property = "addTime", column = "add_time"),
    @Result(property = "finishTime", column = "finish_time")
  })
  @Select("select id,info,status,add_time,deadline,owner, finish_time from todo where id = #{id}")
  ToDo selectOne(Long id);

  /**
   * 按照条件搜索ToDo事件
   * @param conditions id:没有限制  status:事件的状态
   * addTime:between startTime1 and endTime1
   * deadline: between startTime and endTime
   * owner: owner
   * @return
   */
  List<ToDo> selectList(Map<String,Object> conditions);

  /**
   * 按条件搜索ToDo事件的数量
   * @param conditions id:没有限制  status:事件的状态
   * addTime:between startTime1 and endTime1
   * deadline: between startTime and endTime
   * @return
   */
  Integer selectCount(Map<String,Object> conditions);

  /**
   * 插入事项
   * @param todo 事项
   * @return
   */
  @Insert("insert into todo(owner,status,add_time,deadline,info) values(#{owner,jdbcType=VARCHAR},#{status,jdbcType=INTEGER},#{addTime},#{deadline},#{info})")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  Integer insert(ToDo todo);

  @Update("update todo set info = #{info}, deadline = #{deadline}, status = #{status}, finish_time = #{finishTime, jdbcType=TIMESTAMP} where id = #{id}")
  Integer update(ToDo todo);
}
