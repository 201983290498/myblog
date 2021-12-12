package com.coder.blog.service;

import org.springframework.ui.ModelMap;

import java.util.Map;

/**
 * @author coder
 */
public interface DashBoardService {

  /**
   * 获取后台的主页面的数据
   * @param username 用户名
   * @return
   */
  Map<String,Object> getDashBoardInfo(String username);

  /**
   * 获取最近新增博客和访客的趋势 需要获取的参数 dates,blogNumbers,visits
   * @param number 最近的天数
   * @return
   */
  Map<String,Object> recentFrequency(Integer number);

  /**
   * 获取各个类型文章的数量
   * @param map 数据域
   */
  void getBlogCountByStatus(ModelMap map);

  /**
   * 获取用户的待操作事项
   * @param username 用户名
   * @param map 传递数据
   */
  void getToDoList(String username, ModelMap map);
}
