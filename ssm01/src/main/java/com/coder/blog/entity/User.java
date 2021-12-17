package com.coder.blog.entity;

import com.coder.commom.annotation.TableField;
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
@TableField(enableRendering = true)
public class User implements Serializable {

  public static final Integer pageSize = 15;
//TODO 创建个人标签
    /**
     * 用户的id
     */
    private String id;

    /**
     * 邮箱验证
     */
    @TableField(column = "注册邮箱")
    private String email;

    /**
     * 用户名
     */
    @TableField(column = "用户昵称")
    private String username;

    /**
     * 密码
     */
    @TableField(column = "密码")
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
   * 用户的状态，1表示活跃有效，0表示冻结，-1表示删除
   */
  @TableField(column = "账户状态")
  private Integer status;

    /**
     * 入园的年龄
     */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @TableField(column = "入园时间")
    private Date createTime;

    /**
     * 角色 User_roles和权限
     */
    @TableField(column = "用户角色")
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
  public User(String username, Date createTime, String imageId) {
        this.username = username;
        this.createTime = createTime;
        this.imageId = imageId;
    }
}
