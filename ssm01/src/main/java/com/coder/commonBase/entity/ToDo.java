package com.coder.commonBase.entity;

import com.coder.commom.annotation.TableField;
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
@TableField(enableRendering = true)
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
  @TableField(column = "创建者")
  private String owner;

  /**
   * 代做事件的信息
   */
  @TableField(column = "事件内容")
  private String info;

  /**
   * 代做事件的状态 0 已做,1 未作, 其他待定可以代表严重程度
   */
  @TableField(column = "执行状态")
  private Integer status;

  /**
   * 添加的时间
   */
  @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
  @TableField(column = "创建时间")
  private Date addTime;

  /**
   * 最后期限
   */
  @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
  @TableField(column = "最后期限")
  private Date deadline;

  @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
  @TableField(column = "完成时间")
  private Date finishTime;

/**
 * -----------------------------------------------------------------------------------------------
 */
  private String deadlineDescription;

  private String addTimeDescription;

  private Integer color;

  public ToDo(Long id, String info, Date date, Integer status) {
    this.id = id;
    this.info = info;
    this.deadline = date;
    this.status = status;
  }

  public ToDo(Long id, String info, Integer status, Date deadline, Date finishTime) {
    this.id = id;
    this.info = info;
    this.status = status;
    this.deadline = deadline;
    this.finishTime = finishTime;
  }

  public void setColorsIndex(String description){
    if(description.contains("秒")){
      setColor(0);
    }else if(description.contains("分")){
      String[] s = description.split(" ");
      if(Integer.parseInt(s[0])<30){
        setColor(0);
      }else {
        setColor(1);
      }
    }else if(description.contains("时")){
      String[] s = description.split(" ");
      if(Integer.parseInt(s[0])<6){
        setColor(1);
      }else {
        setColor(2);
      }
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
