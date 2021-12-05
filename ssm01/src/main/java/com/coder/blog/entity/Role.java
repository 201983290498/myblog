package com.coder.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author coder
 * @Date 2021/11/26 0:37
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    private Integer pageSize=10;

    private String role;

    private String permission;
}
