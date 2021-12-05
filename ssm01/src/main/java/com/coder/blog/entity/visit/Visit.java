package com.coder.blog.entity.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Visit.
 *
 * @Author coder
 * @Date 2021 /11/26 23:09
 * @Description 记录访问ip的地址, 访问地点 ，访问的浏览器,代理商，访问平台等相关基础的物理设备信息。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer pageSize=10;

    private Integer id;

    private String ip;

    private String userAgent;

    private String city;

    private String url;

    /**浏览器类型*/
    private String browserType;

    /** 平台类型 */
    private String platformType;


    /**
     * 记录登入的时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date time;

    /**
     * 记录登入的session号，区别两次登入
     */
    private String sessionId;

  /**
   * Instantiates a new Visit.
   *
   * @param browserType  the browser type
   * @param platformType the platform type
   */
  public Visit(String browserType, String platformType) {
        this.browserType = browserType;
        this.platformType = platformType;
    }
}
