package com.coder.blog.Utils;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class TimeUtilsTest {

  @Test
  public void getAdaptedDeadline() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(0);
    System.out.println(calendar.getTime());
  }
}
