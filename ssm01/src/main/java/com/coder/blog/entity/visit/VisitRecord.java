package com.coder.blog.entity.visit;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author coder
 * @Date 2021/12/4 15:32
 * @Description 浏览的访问记录
 */
@Data
public class VisitRecord implements Serializable {

    private Integer pageSize=10;

    /**
     * 访问的用户名， 登入前是匿名，登入之后是按用户名来算
     */
    String username;

    /**
     * 登入的ip地址
     */
    String ip;

    /**
     * 访问的url请求
     */
    String url;

    /**
     *  应用的类型
     */
    String applicationType;

    /**
     * url请求资源的类型
     */
    String resourceType;

    /**
     * 额外的信息
     */
    String message;

    /**
     * 访问的时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date time;

    /**
     * sessionI的值
     */
    String sessionId;

    /**
    * 访问资源相关的物理信息
    */
    private Visit visit;
}
