package com.coder.commonBase.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Validation info.
 *
 * @Author coder
 * @Date 2021 /12/1 20:23
 * @Description
 */
@Data
@NoArgsConstructor
public class ValidationInfo {

  public static final Integer pageSize = 15;

    private String message;

    private String email;

    private Long createTime;


  /**
   * Instantiates a new Validation info.
   *
   * @param message    the message
   * @param email      the email
   * @param createTime the create time
   */
  public ValidationInfo(String message, String email, Long createTime) {
        this.message = message;
        this.email = email;
        this.createTime = createTime;
    }
}
