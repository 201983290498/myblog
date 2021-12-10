package com.coder.commom.annotation.Enum;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class ApplicationTypeTest {

    @Test
    public void values() {
        System.out.println(ApplicationType.BLOG);
    }

  /**
   * 测试日期类
   */
  @Test
    public void valueOf() {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(System.currentTimeMillis());
      System.out.println(calendar);
      System.out.println(calendar.getTime());
      calendar.setTime(new Date());
      System.out.println(calendar.getTime());
      //通过字典域改变日期
      calendar.add(Calendar.DATE, -1);
    }
}
