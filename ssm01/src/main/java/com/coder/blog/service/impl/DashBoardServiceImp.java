package com.coder.blog.service.impl;

import com.coder.blog.dao.UserDao;
import com.coder.blog.dao.VisitDao;
import com.coder.blog.entity.User;
import com.coder.blog.service.DashBoardService;
import com.coder.blog.service.VisitRecordService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author coder
 * @Date 2021/12/7 14:39
 * @Description
 */
@Service
@Data
public class DashBoardServiceImp implements DashBoardService {

  private final UserDao userDao;

  private final VisitDao visitDao;

  private final VisitRecordService visitRecordService;

  public DashBoardServiceImp(UserDao userDao, VisitDao visitDao, VisitRecordService visitRecordService) {
    this.userDao = userDao;
    this.visitDao = visitDao;
    this.visitRecordService = visitRecordService;
  }

  @Override
  public Map<String, Object> getDashBoardInfo(String username) {
    //TODO 返回需要的信息
    Map<String, Object> data = new HashMap<>();
    data.put("username", username);
    Integer userNumber = userDao.selectCount();
    data.put("userNumber", userNumber);
    Integer visitNumber = visitDao.selectCount();
    data.put("visitNumber",visitNumber);
    User user = userDao.selectOne(username);
    data.put("imageId",user.getImageId());
    return data;
  }
}
