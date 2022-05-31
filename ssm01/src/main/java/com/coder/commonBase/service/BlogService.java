package com.coder.commonBase.service;

import com.coder.commonBase.entity.Blog;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 结果数据库blog和blogType的相关查询工作
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
  List<Blog> selectAll();

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
   * @param map 1为草稿,1为正文，2是回收文
   * @return
   */
  Integer selectCountByCondition(Map<String,Object> map);

  /**
   * 查最近number的新增博文数量
   * @param number 天数
   * @return 博文数量的队列
   */
    List<Integer> getRecentFrequency(Integer number);

  /**
   * 查所有类别的博客数量，和他们的名字，分成名称列表和数量列表，相应位置一一对应
   * @param aclass 类别对应的枚举类文件
   * @return
   */
  Map<String,Object> selectTypeCount(Class aclass);

  /**
   * 按照月份获取到每个月新增的博客 和 标签
   * @return 返回labels,和data
   */
  Map<String,Object> selectCountByMonth();
}
