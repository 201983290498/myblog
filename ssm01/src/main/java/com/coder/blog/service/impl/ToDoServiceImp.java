package com.coder.blog.service.impl;

import com.coder.blog.Utils.TimeUtils;
import com.coder.blog.dao.ToDoDao;
import com.coder.blog.entity.ToDo;
import com.coder.blog.service.ToDoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author coder
 * @Date 2021/12/11 23:48
 * @Description
 */
@Service
@Data
public class ToDoServiceImp implements ToDoService {

  private final ToDoDao toDoDao;

  public ToDoServiceImp(ToDoDao toDoDao) {
    this.toDoDao = toDoDao;
  }

  @Override
  public ToDo selectOne(Long id) {
    return toDoDao.selectOne(id);
  }

  @Override
  public PageInfo<ToDo> selectList(Map<String,Object> conditions,int page,int size){
    //TODO generate the main methods
    PageHelper.startPage(page,size);
    List<ToDo> toDos = toDoDao.selectList(conditions);
    for(ToDo toDo : toDos){
      toDo.setDeadlineDescription(TimeUtils.getAdaptedDeadline(null,toDo.getDeadline()));
      toDo.setAddTimeDescription(TimeUtils.getAdaptedAddTime(toDo.getAddTime()));
      toDo.setColorsIndex(toDo.getDeadlineDescription());
    }
    return new PageInfo<>(toDos);
  }

  @Override
  public Integer selectCount(Map<String, Object> conditions) {
    //TODO generate the main methods
    return toDoDao.selectCount(conditions);
  }


  @Override
  public Integer insert(ToDo todo) {
    //TODO 主要是填写一些空白的数据
    return toDoDao.insert(todo);
  }


  @Override
  public void update(ToDo todo) {
    toDoDao.update(todo);
    todo.setDeadlineDescription(TimeUtils.getAdaptedDeadline(null,todo.getDeadline()));
    todo.setColorsIndex(todo.getDeadlineDescription());
  }

}
