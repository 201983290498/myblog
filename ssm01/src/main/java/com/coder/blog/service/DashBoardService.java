package com.coder.blog.service;

import java.util.Map;

/**
 * @author coder
 */
public interface DashBoardService {

  /**
   * 获取后台的主页面的数据，
   * @param username 用户名
   * @return
   */
  Map<String,Object> getDashBoardInfo(String username);
}
