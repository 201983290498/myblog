package com.coder.blog.dao;

import com.coder.blog.entity.Role;
import com.coder.blog.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Set;

/**
 * The interface User dao.
 *
 * @author coder
 */
@CacheNamespace(blocking = true)
public interface UserDao {

  /**
   * 根据账户查找一个账户对象,
   * 因为一个邮箱能注册多个，因此不支持邮箱进行唯一登入
   * @param account 可以是id，用户名
   * @return 返回查询的结果 ，不存在的话返回空
   */
  @Results(id="stu01",value = {
            @Result(property = "roles",column = "username",javaType = Set.class ,many = @Many(select = "selectRolesByName") ),
            @Result(property = "username",column="username")
    })
    @Select("select * from tbl_user where username = #{param1} or id=#{param1}")
    User selectOne(String account);

  /**
   * 查询用户表的角色
   *
   * @param username 多表查询的连接词
   * @return 返回角色集合 set
   */
    @Select("select b.* from user_role a ,role_permission b where a.role=b.role and a.username=#{param1}")
    Set<Role> selectRolesByName(String username);

  /**
   * 查用户的总数量
   * @return
   */
  @Select("select count(*) from user_role")
    Integer selectCount();

  /**
   * 查询所有用户
   *
   * @return list
   */
  @ResultMap(value="stu01")
    @Select("select * from tbl_user")
    List<User> selectList();

  /**
   * 插入新的用户
   *
   * @param user 用户信息
   */
  @Insert("insert into tbl_user(id,email,username,password,imageId,personalInfo,createTime) values(#{id},#{email},#{username},#{password},#{imageId,jdbcType=VARCHAR},#{personalInfo,jdbcType=VARCHAR},#{createTime})")
    void insert(User user);


  /**
   * 插入新增用户的权限
   *
   * @param username 用户名
   * @param role     角色
   */
  @Insert("insert into user_role(username,role) values(#{arg0},#{arg1})")
    void insertRole(String username,String role);

}
