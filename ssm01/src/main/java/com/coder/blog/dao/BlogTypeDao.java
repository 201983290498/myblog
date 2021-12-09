package com.coder.blog.dao;

import com.coder.blog.entity.BlogType;
import org.apache.ibatis.annotations.Select;

/**
 * @author coder
 */
public interface BlogTypeDao {

  String tableName = "blog_tpe";

  @Select("select id,typename,num from blog_type where id=#{tyepId}}")
  BlogType selectOne(Long typeId);


}
