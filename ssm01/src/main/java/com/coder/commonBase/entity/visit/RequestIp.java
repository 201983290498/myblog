package com.coder.commonBase.entity.visit;

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

    public static final long serialVersionUID = 1L;

    public static final Integer pageSize = 15;

    private String ip;

    private long createTime;

    private Integer reCount;
}
