package com.coder.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author coder
 * @Date 2021/12/1 20:23
 * @Description
 */
@Data
@NoArgsConstructor
public class ValidationInfo {

    private String message;

    private String email;

    private Long createTime;

    public ValidationInfo(String message, String email, Long createTime) {
        this.message = message;
        this.email = email;
        this.createTime = createTime;
    }
}
