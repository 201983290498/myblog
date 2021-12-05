package com.coder.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * The type Image.
 *
 * @Author coder
 * @Date 2021 /11/30 8:06
 * @Description
 */
@Data
public class Image implements Serializable {

    private Integer pageSize=10;

    private String id;

    private byte[] bytes;

  /**
   * Instantiates a new Image.
   *
   * @param bytes the bytes
   */
  public Image(byte[] bytes) {
        this.bytes = bytes;
    }

  /**
   * Instantiates a new Image.
   *
   * @param id    the id
   * @param bytes the bytes
   */
  public Image(String id, byte[] bytes) {
        this.id = id;
        this.bytes = bytes;
    }
}
