package com.coder.blog.service;

import com.coder.blog.entity.Blog;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.Map;

/**
 * @author coder
 */
public interface BlogService {

  /**
   * 查看一篇博客的具体内容
   * @param id 博客的id
   * @return
   */
  Blog selectOne(Long id);

  /**
   * 按页获取所有的博客，按照时间顺序降序,只有正文
   * @return
   */
  PageInfo<Blog> selectAllPage(int page,int size);

  /**
   * 安业获取某个用户的博文
   * @param username 用户名
   * @return
   */
  PageInfo<Blog> selectListPageByUsername(String username,Integer status,int page,int size);

  /**
   * 查询最近一天博文的数量
   * @return
   */
  Integer selectCountNow();

  /**
   * 查询某段时间之间博文的数量
   * @param map
   * startTime 搜索的开始时间
   * endTime 搜索的结束时间
   * status 文章的状态
   * username 博客的拥有者
   * type 博客的类型
   * @return
   */
  Integer selectCountByTime(Map<String,Object> map);

  /**
   * 查询博文的总数量
   * @param status 1为草稿,1为正文，2是回收文
   * @return
   */
  Integer selectCountByCondition(Map<String,Object> map);

}
