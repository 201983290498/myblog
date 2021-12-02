package com.coder.blog.entity.visit;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author coder
 * @Date 2021/11/26 21:31
 * @Description
 */
@Data
public class BlackIp implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer vid;

    private String ip;

    private String city;

    private String browsertype;

    private String platformtype;

    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date time;
}
