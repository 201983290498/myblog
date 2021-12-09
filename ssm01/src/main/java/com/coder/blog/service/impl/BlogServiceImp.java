package com.coder.blog.service.impl;

import com.coder.blog.dao.BlogDao;
import com.coder.blog.entity.Blog;
import com.coder.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.stereotype.Service;

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
  public PageInfo<Blog> selectListPage(int page,int size) {
    PageHelper.startPage(page,size);
    PageInfo<Blog> info = new PageInfo<>(blogDao.selectAll());
    return info;
  }

  @Override
  public PageInfo<Blog> selectListPageByUsername(String username,int page,int size) {
    PageHelper.startPage(page,size);
    PageInfo<Blog> info = new PageInfo<>(blogDao.selectListByUsername(username));
    return info;
  }

  @Override
  public Integer selectCountNow() {
    // TODO 查找最近一天的记录数量
    Map<String,Object>map = new HashMap();
    return null;
  }

  @Override
  public Integer selectCountByTime(Map<String, Object> map) {
    return null;
  }

  @Override
  public Integer selectCount(Integer status) {
    return blogDao.selectCount(status);
  }
}
