package com.coder.blog.service;

import com.coder.blog.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author coder
 * @Date 2021/11/25 20:32
 * @Description
 */
public interface UserService {

     /**
      * 登入
      * @param user 用户
      * @return 是否登入成功
      */
     Boolean login(User user);

     /**
      * 选一个账户
      * @param account 通过账户选择account
      * @return 返回值为用户
      */
     User selectOne(String account);

     /**
      * 插入一个图片，和需要打包的文件
      * @param user 用户名
      * @param photo 上传打包的file文件
      */
     void insert(User user, MultipartFile photo) throws IOException;

     /**
      * 返回一个User
      * @param user 用户名
      */
     void insert(User user);
}
