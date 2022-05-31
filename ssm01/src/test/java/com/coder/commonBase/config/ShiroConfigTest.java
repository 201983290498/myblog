package com.coder.commonBase.config;

import org.junit.Test;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class ShiroConfigTest {

  @Test
  public void testProperties(){
    try {
      Properties properties = PropertiesLoaderUtils.loadAllProperties("C:\\Users\\coder\\Desktop\\ssm1\\ssm01\\src\\main\\resources\\shiro.properties");
      System.out.println(properties);
      System.out.println(properties.values());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Test
  public void testDate(){
    System.out.println(new Date(System.currentTimeMillis()));
    System.out.println(new java.sql.Date(new Date().getTime()));
  }

}
