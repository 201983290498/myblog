package com.coder.commonBase.entity.visit;

import com.coder.commom.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Visit record.
 * 浏览访问记录
 *
 * @Author coder
 * @Date 2021 /12/4 15:32
 * @Description 浏览的访问记录
 */
@Data
@TableField(enableRendering = true)
public class VisitRecord implements Serializable {

  public static final Integer pageSize = 15;

  /**
   * 访问的用户名， 登入前是匿名，登入之后是按用户名来算
   */
  @TableField(column="访客")
  String username;

  /**
   * 登入的ip地址
   */
  @TableField(column="访问ip")
  String ip;

  /**
   * 访问的url请求
   */
  @TableField(column="请求地址")
  String url;

  /**
   * 应用的类型
   */
  @TableField(column="应用平台")
  String applicationType;

  /**
   * url请求资源的类型
   */
  @TableField(column = "资源类型")
  String resourceType;



    /**
     * 访问的时间
     */
    @TableField(column = "访问时间")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date time;

  /**
   * sessionI的值
   */
  @TableField(column = "访问session")
  String sessionId;

    /**
    * 访问资源相关的物理信息
    */
    private Visit visit;

  /**
   * 额外的信息
   */
  @TableField(column = "备注")
  String message;
}
