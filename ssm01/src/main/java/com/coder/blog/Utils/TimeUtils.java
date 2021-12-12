package com.coder.blog.Utils;

import com.coder.blog.entity.ToDo;
import lombok.Data;

import java.util.Date;

/**
 * @Author coder
 * @Date 2021/12/12 0:22
 * @Description
 */
@Data
public class TimeUtils {

  /**
   * 获取start到deadline的时间间隔描述字符窜
   * @param timestamp 剩下的时间戳
   * @param suffix 字符窜后缀
   * @return
   */
  public static String getAdaptedString(Long timestamp,String suffix) {
    Long[] limit = new Long[]{60L,3600L,86400L,30*86400L, 30*12*86400L};
    if(timestamp<0){
      return "已过期";
    }
    else if(timestamp < limit[0] ){
      return timestamp + " 秒" + suffix;
    }else if(timestamp < limit[1]){
      return (Long) (timestamp/limit[0]) + " 分钟" +suffix;
    }else if (timestamp < limit[2]){
      return (Long) (timestamp/limit[1]) + " 小时" + suffix;
    }else if(timestamp < limit[3]){
      return (Long) (timestamp/limit[2]) + " 天" + suffix;
    }else if(timestamp < limit[4]){
      return (Long) (timestamp/limit[3]) + " 月" + suffix;
    }else{
      return (Long) (timestamp/limit[4]) + " 年" + suffix;
    }
  }

  public static String getAdaptedDeadline(Date now,Date deadline) {
    if(now == null) {
      now = new Date();
    }
    long timestamp = (deadline.getTime() - now.getTime())/1000;
    return getAdaptedString(timestamp,"后");
  }

  public static String getAdaptedAddTime(Date addTime){
    long timestamp = ((new Date()).getTime()-addTime.getTime())/1000;
    return getAdaptedString(timestamp,"前");
  }
}
