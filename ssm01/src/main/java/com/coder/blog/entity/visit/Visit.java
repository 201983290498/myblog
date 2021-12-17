package com.coder.blog.entity.visit;

import com.coder.commom.annotation.TableField;
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
@TableField(enableRendering = true)
public class Visit implements Serializable {
    public static final long serialVersionUID = 1L;

  public static final Integer pageSize = 15;


    private Integer id;

    @TableField(column = "访问IP")
    private String ip;

    private String userAgent;

    @TableField(column = "城市")
    private String city;

    private String url;

    /**浏览器类型*/
    @TableField(column = "浏览器")
    private String browserType;

    /** 平台类型 */
    @TableField(column = "平台")
    private String platformType;


    /**
     * 记录登入的时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @TableField(column = "登入时间")
    private Date time;

    /**
     * 记录登入的session号，区别两次登入
     */
    @TableField(column = "Session标识符")
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
