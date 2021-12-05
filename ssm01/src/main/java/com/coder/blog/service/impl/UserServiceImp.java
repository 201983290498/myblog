package com.coder.blog.service.impl;

import com.coder.blog.dao.ImageDao;
import com.coder.blog.dao.UserDao;
import com.coder.blog.entity.Image;
import com.coder.blog.entity.User;
import com.coder.blog.service.ImageService;
import com.coder.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * The type User service imp.
 *
 * @Author coder
 * @Date 2021 /11/26 23:11
 * @Description
 */
@Service("userServiceImp")
@Data
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    private final ImageService imageService;

  /**
   * Instantiates a new User service imp.
   *
   * @param userDao      the user dao
   * @param imageService the image service
   */
  public UserServiceImp(UserDao userDao, ImageService imageService) {
    this.userDao = userDao;
    this.imageService = imageService;
  }

  /**
     * 登入
     * @param user
     * @return
     */
    @Override
    public Boolean login(User user) {
        User user1 = userDao.selectOne(user.getUsername());
        if(user1!=null&&user1.getPassword().equals(user.getPassword())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 查找账户
     * @param account
     * @return
     */
    @Override
    public User selectOne(String account) {
        return userDao.selectOne(account);
    }

    @Override
    public PageInfo<User> selectAllByPage(int page, int size) {
      // TODO List
      PageHelper.startPage(page,size);
      return new PageInfo<>(userDao.selectList());
    }


  @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Transactional
    @Override
    public void insert(User user, MultipartFile photo){
        //添加图片
        if(photo.getSize()!=0){
            try {
                String upload = imageService.upload(photo);
                user.setImageId(upload);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //添加角色
        insert(user);
        userDao.insertRole(user.getUsername(),"user");
    }


    @Override
    public void insert(User user) {
        user.setCreateTime(new Date(System.currentTimeMillis()));
        user.setId(UUID.randomUUID().toString().substring(0,10));
        userDao.insert(user);
    }
}
