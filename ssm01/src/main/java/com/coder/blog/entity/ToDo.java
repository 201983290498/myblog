package com.coder.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 代做的事件, 时间计划表适用于所有用户
 * @Author coder
 * @Date 2021/12/11 22:27
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo implements Serializable {

  static final long serialVersionUID = 1L;

  public static final int pageSize =8;

  /**
   * 代做事件的唯一标识
   */
  private Long id;

  /**
   * 代做事件的拥有者
   */
  private String owner;

  /**
   * 代做事件的信息
   */
  private String info;

  /**
   * 代做事件的状态 0 已做,1 未作, 其他待定可以代表严重程度
   */
  private Integer status;

  /**
   * 添加的时间
   */
  @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
  private Date addTime;

  /**
   * 最后期限
   */
  @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
  private Date deadline;

/**
 * -----------------------------------------------------------------------------------------------
 */
  private String deadlineDescription;

  private String addTimeDescription;

  private Integer color;

  public void setColorsIndex(String description){
    if(description.contains("秒")){
      setColor(0);
    }else if(description.contains("分")){
      setColor(1);
    }else if(description.contains("时")){
      setColor(2);
    }else if(description.contains("天")){
      setColor(3);
    }else if(description.contains("月")){
      setColor(4);
    }else if(description.contains("年")){
      setColor(5);
    }else{
      setColor(5);
    }
  }
}
