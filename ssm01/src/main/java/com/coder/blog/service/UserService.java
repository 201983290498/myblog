package com.coder.blog.service;

import com.coder.blog.entity.User;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * The interface User service.
 *
 * @Author coder
 * @Date 2021 /11/25 20:32
 * @Description
 */
public interface UserService {

  /**
   * 登入
   *
   * @param user 用户
   * @return 是否登入成功 boolean
   */
  Boolean login(User user);

  /**
   * 选一个账户
   *
   * @param account 通过账户选择account
   * @return 返回值为用户 user
   */
  User selectOne(String account);

  /**
   * Select all by page page info.
   *
   * @param page the page
   * @param size the size
   * @return the page info
   */
  PageInfo<User> selectAllByPage(int page,int size);
  List<User> selectAll();

  /**
   * 查看用户的总数量
   * @return
   */
  Integer selectCount();

  /**
   * 插入一个图片，和需要打包的文件
   *
   * @param user  用户名
   * @param photo 上传打包的file文件
   * @throws IOException the io exception
   */
  void insert(User user, MultipartFile photo) throws IOException;

  /**
   * 返回一个User
   *
   * @param user 用户名
   */
  void insert(User user);
}
