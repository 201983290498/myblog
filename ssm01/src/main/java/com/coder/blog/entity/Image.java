package com.coder.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author coder
 * @Date 2021/11/30 8:06
 * @Description
 */
@Data
public class Image implements Serializable {

    private String id;

    private byte[] bytes;

    public Image(byte[] bytes) {
        this.bytes = bytes;
    }

    public Image(String id, byte[] bytes) {
        this.id = id;
        this.bytes = bytes;
    }
}
