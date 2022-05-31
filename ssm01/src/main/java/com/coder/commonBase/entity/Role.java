package com.coder.commonBase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Role.
 *
 * @Author coder
 * @Date 2021 /11/26 0:37
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

  public static final Integer pageSize = 15;

    private String role;

    private String permission;

  @Override
  public String toString() {
    return  role.toString();
  }
}
