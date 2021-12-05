package com.coder.blog.dao;

import com.coder.blog.entity.Role;
import com.coder.blog.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Set;

/**
 * @author coder
 */
@CacheNamespace(blocking = true)
public interface UserDao {

    /**
     * 根据账户查找一个账户对象
     * @param account 可以是邮箱，id，用户名
     * @return 返回查询的结果，不存在的话返回空
     */
    @Results(id="stu01",value = {
            @Result(property = "roles",column = "username",javaType = Set.class ,many = @Many(select = "selectRolesByName", fetchType = FetchType.LAZY) )
    })
    @Select("select * from tbl_user where username = #{param1} or email=#{param1} or id=#{param1}")
    User selectOne(String account);

    /**
     * 查询用户表的角色
     * @param username 多表查询的连接词
     * @return 返回角色集合
     */
    @Select("select b.* from user_role a ,role_permission b where a.role=b.role and a.username=#{param1}")
    Set<Role> selectRolesByName(String username);

    /**
     * 插入新的用户
     * @param user 用户信息
     */
    @Insert("insert into tbl_user(id,email,username,password,imageId,personalInfo,createTime) values(#{id},#{email},#{username},#{password},#{imageId,jdbcType=VARCHAR},#{personalInfo,jdbcType=VARCHAR},#{createTime})")
    void insert(User user);


    /**
     * 插入新增用户的权限
     * @param username 用户名
     * @param role 角色
     */
    @Insert("insert into user_role(username,role) values(#{arg0},#{arg1})")
    void insertRole(String username,String role);
}
