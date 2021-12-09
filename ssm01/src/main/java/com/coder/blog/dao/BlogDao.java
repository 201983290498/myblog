package com.coder.blog.dao;

import com.coder.blog.entity.Blog;
import com.coder.blog.entity.BlogType;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author coder
 * @Date 2021/12/9 15:55
 * @Description
 */
@CacheNamespace(blocking = true)
public interface BlogDao {

  /**
   * 查找一个用户的多篇博客
   * @return 返回所有的博文记录
   * @param
   */
  @Results(id = "blogMap",value = {
    @Result(property = "clickNum",column = "click_num"),
    @Result(property = "commentNum",column = "comment_num"),
    @Result(property = "agreeNum",column = "agree_num"),
    @Result(property = "isTop",column = "istop"),
    @Result(property = "isRecommend", column = "isrecommend"),
    @Result(property = "addTime",column = "add_time"),
    @Result(property = "updateTime",column = "update_time"),
    @Result(property = "type",column = "type_id",javaType = BlogType.class,one = @One(select="com.coder.blog.dao.BlogTypeDao.selectOne")),
    @Result(property = "imageIds", column = "id",many = @Many(select = "selectImageList"),javaType = List.class)
  })
  @Select("select * from blog where status = 1 order by add_time desc")
  List<Blog> selectAll();

  /**
   * 根据博文的id搜索某一篇博文
   * @param id 博文的唯一表示id
   * @return
   */
  @ResultMap("blogMap")
  @Select("select * from blog where id = #{id}")
  Blog selectOne(Long id);

  /**
   * status所有某个status的类型，默认是正文，也可以搜索回收文，也可以是所有的文章
   * 总的博客数量,可以是各种类型，所以这里采用mapper注入
   * @param status -1为草稿,1为正文，2是回收文
   * @return
   */
  Integer selectCount(Integer status);

  /**
   * 搜索最新的用户总数量
   * @param map
   * startTime 搜索的开始时间
   * endTime 搜索的结束时间
   * status 文章的状态
   * username 博客的拥有者
   * type 博客的类型
   * @return 返回值是数量
   */
  Integer selectCountByTime(Map<String,Object> map);

  /**
   * 查找某个用户的博客
   * @param username
   * @return
   */
  @ResultMap(value = "blogMap")
  @Select("select * from blog where username=#{username} order by add_time desc")
  List<Blog> selectListByUsername(String username);




  @Select("select imageId from blog_image where blogId = #{blogId}")
  List<String> selectImageList(Long blogId);
}
