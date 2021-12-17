package com.coder.blog.service.impl;

import com.coder.blog.dao.BlogDao;
import com.coder.blog.dao.BlogTypeDao;
import com.coder.blog.entity.Blog;
import com.coder.blog.entity.BlogType;
import com.coder.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.stereotype.Service;


import java.lang.reflect.Field;
import java.util.*;

/**
 * @Author coder
 * @Date 2021/12/10 0:08
 * @Description
 */
@Service
@Data
public class BlogServiceImp implements BlogService {

  private final BlogDao blogDao;
  private final BlogTypeDao blogTypeDao;

  public BlogServiceImp(BlogDao blogDao,BlogTypeDao blogTypeDao) {
    this.blogDao = blogDao;
    this.blogTypeDao = blogTypeDao;
  }

  @Override
  public Blog selectOne(Long id) {
    return blogDao.selectOne(id);
  }

  @Override
  public PageInfo<Blog> selectAllPage(int page,int size) {
    PageHelper.startPage(page,size);
    PageInfo<Blog> info = new PageInfo<>(blogDao.selectAll());
    return info;
  }
  @Override
  public List<Blog> selectAll(){
    return blogDao.selectAll();
  }

  @Override
  public PageInfo<Blog> selectListPageByUsername(String username,Integer status,int page,int size) {
    if(status == null) {
      status = 1;
    }
    PageHelper.startPage(page,size);
    PageInfo<Blog> info = new PageInfo<>(blogDao.selectListByUsername(username,status));
    return info;
  }

  @Override
  public Integer selectCountNow() {
    Map<String,Object>map = new HashMap<>();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    map.put("endTime",calendar.getTime());
    calendar.add(Calendar.DATE,-1);
    map.put("startTime", calendar.getTime());
    map.put("status",1);
    return blogDao.selectCountByTime(map);
  }

  @Override
  public Integer selectCountByTime(Map<String, Object> map) {
    return blogDao.selectCountByTime(map);
  }

  @Override
  public Integer selectCountByCondition(Map<String, Object> map){
    return blogDao.selectCount((Integer) map.get("status"));
  }

  @Override
  public List<Integer> getRecentFrequency(Integer number) {
    List<Integer> list = new ArrayList<>();
    Map<String, Object> map = new HashMap<>();
    //博客的状态
    map.put("status",1);
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.set(Calendar.HOUR,0);
    do{
      map.put("endTime",cal.getTime());
      cal.add(Calendar.DATE,-1);
      map.put("startTime",cal.getTime());
      list.add(selectCountByTime(map));
      number = number - 1;
    }while(number!=0);
    return list;
  }

  /**
   * 查所有类别的博客数量，和他们的名字，分成名称列表和数量列表，相应位置一一对应
   *
   * @param aclass 类别对应的枚举类文件
   * @return
   */
  @Override
  public Map<String, Object> selectTypeCount(Class aclass) {
    Map<String,Object> result = new HashMap<String,Object>();
    List<String> typeNames = new ArrayList<String>();
    List<Integer> numbers = new ArrayList<>();
    Field[] fields = aclass.getFields();
    int total = 0;
    for(Field field : fields) {
      String name = field.getName();
      //返回枚举类的字段名，字符窜也行，直接输出
      typeNames.add(name);
      BlogType blogType = blogTypeDao.selectTypeCount(name);
      numbers.add(blogType.getNum()+1);
      total += blogType.getNum();
    }
    result.put("typeNames",typeNames);
    result.put("numbers",numbers);
    return result;
  }

  /**
   * 按照月份获取到每个月新增的博客 和 标签
   *
   * @return 返回labels, 和data
   */
  @Override
  public Map<String, Object> selectCountByMonth() {
    Map<String, Object> result = new HashMap<>();
    List<String> labels = new ArrayList<>();
    List<Integer> data = new ArrayList<>();
    Map<String,Object> conditions = new HashMap<>();
    conditions.put("status",1);
    //获取下一个月的时间
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.set(Calendar.DATE,1);
    cal.add(Calendar.MONTH,1);
    //获取十二个月的数据
    for(int i=0;i<12;i++){
      conditions.put("endTime",cal.getTime());
      cal.add(Calendar.MONTH,-1);
      conditions.put("startTime",cal.getTime());
      labels.add(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1));
      data.add(blogDao.selectCountByTime(conditions));
    }
    Collections.reverse(labels);
    Collections.reverse(data);
    result.put("labels", labels);
    result.put("data",data);
    return result;
  }
}
