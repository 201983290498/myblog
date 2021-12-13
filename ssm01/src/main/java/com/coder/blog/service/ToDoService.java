package com.coder.blog.service;


import com.coder.blog.entity.ToDo;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * TODO check
 * 待做事项的仓库
 * @author coder
 */
public interface ToDoService {

    /**
     * 代做事项列表
     * @param id 代做事项的id
     * @return
     */
    ToDo selectOne(Long id);

  /**
   * 按照条件搜索ToDo事件
   * @param conditions 查询条件
   * id:没有限制  status:事件的状态,默认是大于0,等于0需要自己写
   * addTime:between startTime1 and endTime1
   * deadline: between startTime and endTime
   * owner: owner
   * order: 按照有个字段排序 deadline, add_time 默认是 deadline
   * @return
   */
  PageInfo<ToDo> selectList(Map<String,Object> conditions,int page,int size);

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
  Integer insert(ToDo todo);

  /**
   * 更新待做的事项
   * 同时更新待做的信息 事件描述和时间
   * @param todo 待做的事项
   */
  void update(ToDo todo);
}
