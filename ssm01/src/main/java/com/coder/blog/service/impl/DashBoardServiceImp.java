package com.coder.blog.service.impl;

import com.coder.blog.dao.UserDao;
import com.coder.blog.dao.VisitDao;
import com.coder.blog.entity.ToDo;
import com.coder.blog.entity.User;
import com.coder.blog.service.*;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.text.DecimalFormat;
import java.util.*;

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

  private final VisitService visitService;

  private final VisitRecordService visitRecordService;

  private final BlogService blogService;

  private final ToDoService toDoService;

  public DashBoardServiceImp(UserDao userDao, VisitDao visitDao, VisitRecordService visitRecordService,
                             BlogService blogService,VisitService visitService,ToDoService toDoService) {
    this.userDao = userDao;
    this.visitDao = visitDao;
    this.visitRecordService = visitRecordService;
    this.visitService = visitService;
    this.blogService = blogService;
    this.toDoService = toDoService;
  }

  @Override
  public Map<String, Object> getDashBoardInfo(String username) {
    Map<String, Object> data = new HashMap<>();
    //获取用户名
    data.put("username", username);
    //获取用户的总数量
    Integer userNumber = userDao.selectCount();
    data.put("userNumber", userNumber);
    //获取访客的总数量
    Integer visitNumber = visitDao.selectCount();
    data.put("visitNumber",visitNumber);
    //获取user的图片
    User user = userDao.selectOne(username);
    data.put("imageId",user.getImageId());
    //获取一天的博文数量
    data.put("blogNumber",blogService.selectCountNow());
    //获取尽一天的访问频率。
    data.put("visitFrequency",visitRecordService.selectCountNow());
    return data;
  }

  /**
   * 获取最近新增博客和访客的趋势
   * @param number 最近的天数  需要获取的参数 dates,blogNumbers,visits
   * @return
   */
  @Override
  public Map<String, Object> recentFrequency(Integer number) {
    Map<String,Object> map = new HashMap<>();
    List<String> dateList = new ArrayList<>();
    Calendar cal = Calendar.getInstance();
    List<Integer> blogList = blogService.getRecentFrequency(number);
    List<Integer> recentFrequency = visitService.getRecentFrequency(number);
    cal.setTimeInMillis(System.currentTimeMillis());
    do{
      dateList.add((cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE));
      cal.add(Calendar.DATE,-1);
      number = number -1;
    }while(number!=0);
    Collections.reverse(dateList);
    Collections.reverse(blogList);
    Collections.reverse(recentFrequency);
    map.put("dates",dateList);
    map.put("blogNumbers",blogList);
    map.put("visits",recentFrequency);
    return map;
  }

  /**
   * 获取各个类型文章的数量
   *
   * @param map 数据域
   */
  @Override
  public void getBlogCountByStatus(ModelMap map) {
    Map<String,Object> conditions = new HashMap<>();
    Double[] b = new Double[3];
    Double total;
    conditions.put("status",-1);
    b[0] = Double.valueOf(blogService.selectCountByCondition(map));
    conditions.put("status",1);
    b[1] = Double.valueOf(blogService.selectCountByCondition(map));
    conditions.put("status",2);
    b[2] = Double.valueOf(blogService.selectCountByCondition(map));
    total = b[0] + b[1] + b[2];
    for(int i=0; i<3; i++){
      if(total == 0){
        b[i] = 100.0/b.length;
      }else{
        b[i] = b[i]/total;
      }
    }
    DecimalFormat df = new DecimalFormat("#.0");
    map.put("blogNum1",df.format(b[0]));
    map.put("blogNum2",df.format(b[1]));
    map.put("blogNum3",df.format(b[2]));
  }


  /**
   * TODO check
   */
  @Override
  public void getToDoList(String username, ModelMap map) {
    Map<String, Object> conditions = new HashMap<>();
    conditions.put("owner", username);
    PageInfo<ToDo> toDoPageInfo = toDoService.selectList(conditions, 1, ToDo.pageSize);
    map.put("toDoList",toDoPageInfo);
  }
}
