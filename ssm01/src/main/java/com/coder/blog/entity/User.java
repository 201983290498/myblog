package com.coder.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * The type User.
 *
 * @Author coder
 * @Date 2021 /11/25 20:15
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Integer pageSize=10;

    /**
     * 用户的id
     */
    private String id;

    /**
     * 邮箱验证
     */
    private String email;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 人物的头像
     */
    private String imageId;

    /**
     * 个人简介
     */
    private String personalInfo;

    /**
     * 入园的年龄
     */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    /**
     * 角色 User_roles和权限
     */
    private Set<Role> roles;

  /**
   * Instantiates a new User.
   *
   * @param username the username
   * @param password the password
   */
  public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

  /**
   * Instantiates a new User.
   *
   * @param id           the id
   * @param email        the email
   * @param username     the username
   * @param password     the password
   * @param imageId      the image id
   * @param personalInfo the personal info
   * @param createTime   the create time
   */
  public User(String id, String email, String username, String password, String imageId, String personalInfo, Date createTime) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.imageId = imageId;
        this.personalInfo = personalInfo;
        this.createTime = createTime;
    }

  /**
   * Instantiates a new User.
   *
   * @param username   the username
   * @param createTime the create time
   */
  public User(String username, Date createTime) {
        this.username = username;
        this.createTime = createTime;
    }
}
