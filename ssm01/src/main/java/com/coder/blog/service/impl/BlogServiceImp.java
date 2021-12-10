package com.coder.blog.service.impl;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.coder.blog.dao.BlogDao;
import com.coder.blog.entity.Blog;
import com.coder.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.stereotype.Service;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author coder
 * @Date 2021/12/10 0:08
 * @Description
 */
@Service
@Data
public class BlogServiceImp implements BlogService {

  private final BlogDao blogDao;

  public BlogServiceImp(BlogDao blogDao) {
    this.blogDao = blogDao;
  }

  @Override
  public Blog selectOne(Long id) {
    return blogDao.selectOne(id);
  }

  @Override
  public PageInfo<Blog> selectAllPage(int page,int size) {
    PageHelper.startPage(page,size);
    PageInfo<Blog> info = new PageInfo<>(blogDao.selectAll());
    return info;
  }

  @Override
  public PageInfo<Blog> selectListPageByUsername(String username,Integer status,int page,int size) {
    if(status == null) {
      status = 1;
    }
    PageHelper.startPage(page,size);
    PageInfo<Blog> info = new PageInfo<>(blogDao.selectListByUsername(username,status));
    return info;
  }

  @Override
  public Integer selectCountNow() {
    Map<String,Object>map = new HashMap<>();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    map.put("startTime",calendar.getTime());
    calendar.add(Calendar.DATE,-1);
    map.put("endTime", calendar.getTime());
    map.put("status",1);
    return blogDao.selectCountByTime(map);
  }

  @Override
  public Integer selectCountByTime(Map<String, Object> map) {
    return blogDao.selectCountByTime(map);
  }

  @Override
  public Integer selectCountByCondition(Map<String, Object> map){
    return blogDao.selectCount((Integer) map.get("status"));
  }
}
