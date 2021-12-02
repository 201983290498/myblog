package com.coder.blog.entity.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author coder
 * @Date 2021/11/26 23:09
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String ip;

    private String userAgent;

    private String city;

    private String url;

    private String browserType;//浏览器类型


    private String platformType;//平台类型


    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date time;

    public Visit(String browserType, String platformType) {
        this.browserType = browserType;
        this.platformType = platformType;
    }
}
