package com.coder.blog.service;

import com.coder.blog.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    Image selectOne(String id);

    String upload(Image image);

    String upload(MultipartFile photo) throws IOException;
}
