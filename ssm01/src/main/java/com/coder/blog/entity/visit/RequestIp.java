package com.coder.blog.entity.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Request ip.
 *
 * @Author coder
 * @Date 2021 /11/27 11:43
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestIp implements Serializable {

    private String ip;

    private long createTime;

    private Integer reCount;
}
