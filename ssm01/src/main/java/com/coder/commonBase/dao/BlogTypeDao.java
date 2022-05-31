package com.coder.commonBase.dao;

import com.coder.commonBase.entity.BlogType;
import org.apache.ibatis.annotations.*;

import java.util.Map;

/**
 * @author coder
 */
public interface BlogTypeDao {

  String tableName = "blog_tpe";

// ----------------------------------------  博客的分类相关 -----------

  /**
   * 查看每个分类的数量
   * @param typeName
   * @return
   */
  @Select("select * from blog_type where typename = #{typeName}")
  @ResultMap("blogType")
  BlogType selectTypeCount(String typeName);

  /**
   * 插入一条博客类型
   * @param type 博客类型
   */
  @Insert("insert into blog_type(typename,num,add_time) values(#{typename},#{num,jdbcType=INTEGER},#{addTime})")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  int insert(BlogType type);

  /**
   * 搜索BlogType
   * @param map id或者typename
   */
  @Select("<script>select * from blog_type " +
    "<where>" +
    "<if test='id!=null'>and id = #{id}</if>" +
    "<if test='typename!=null'>and typename=#{typename}</if>" +
    "</where>" +
    "</script>")
  @Results(id="blogType",value = {
    @Result(property = "addTime", column = "add_time"),
  })
  BlogType selectOneBlogType(Map<String,Object> map);

  /**
   * 更新 blogType的数量
   * @param type 更新博客类型
   * @return 返回修改的行
   */
  @Update("update blog_type set typename = #{typename}, num = #{num}, add_time = #{addTime} where id = #{id}")
  int updateBlogType(BlogType type);
}
