package com.coder.blog.service;

import com.coder.blog.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The interface Image service.
 */
public interface ImageService {

  /**
   * Select one image.
   *
   * @param id the id
   * @return the image
   */
  Image selectOne(String id);

  /**
   * Upload string.
   *
   * @param image the image
   * @return the string
   */
  String upload(Image image);

  /**
   * Upload string.
   *
   * @param photo the photo
   * @return the string
   * @throws IOException the io exception
   */
  String upload(MultipartFile photo) throws IOException;
}
