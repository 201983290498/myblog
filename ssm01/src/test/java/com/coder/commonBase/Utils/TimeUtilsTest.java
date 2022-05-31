package com.coder.commonBase.Utils;

import org.junit.Test;

import java.util.Calendar;

public class TimeUtilsTest {

  @Test
  public void getAdaptedDeadline() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(0);
    System.out.println(calendar.getTime());
  }
}
