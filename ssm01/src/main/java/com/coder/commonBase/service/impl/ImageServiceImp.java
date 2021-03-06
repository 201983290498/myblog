package com.coder.commonBase.service.impl;

import com.coder.commonBase.dao.ImageDao;
import com.coder.commonBase.entity.Image;
import com.coder.commonBase.service.ImageService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * The type Image service imp.
 *
 * @Author coder
 * @Date 2021 /11/30 8:16
 * @Description
 */
@Service
@Data
public class ImageServiceImp implements ImageService {

    @Autowired
    private ImageDao imageDao;

    /**
     *  获取图片
     * @param id
     * @return
     */
    @Override
    public Image selectOne(String id) {
        return imageDao.selectOne(id);
    }

    /**
     * 图片上传
     * @param image
     * @return
     */
    @Override
    public String upload(Image image) {
        String id = UUID.randomUUID().toString();
        while(imageDao.findExistOne(id)!=0){
            id = UUID.randomUUID().toString();
        }
        image.setId(id);
        imageDao.insert(image);
        return id;
    }

    @Override
    public String upload(MultipartFile photo) throws IOException {
        return upload(new Image(photo.getBytes()));
    }
}
