package com.coder.blog.controller;

import com.coder.blog.Utils.RespMessageUtils;
import com.coder.blog.entity.Image;
import com.coder.blog.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
/**
 * @Author coder
 * @Date 2021/11/30 8:15
 * @Description
 */
@Controller
@Api("图片上下传")
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/{imageId}")
    @ApiOperation(value = "图片下载", notes = "根据账户获取头像", httpMethod = "GET")
    public ResponseEntity<byte[]> loginFigureImage(@PathVariable("imageId") String id){
        Image image = imageService.selectOne(id);
        MultiValueMap<String,String> header = new HttpHeaders();
        header.add("Content-Disposition", "attachment;filename=figureImage.jpg");
        HttpStatus ok = HttpStatus.OK;
        ResponseEntity<byte[]> response = new ResponseEntity<>(image.getBytes(),header,ok);
        return response;
    }

    @ResponseBody
    @PostMapping("/upload")
    @ApiOperation(value="图片上传", notes = "图片上传", httpMethod = "POST")
    public String upLoadImage(MultipartFile photo) throws IOException {
        String upload = imageService.upload(new Image(photo.getBytes()));//传递回去上传成功的信息
        return RespMessageUtils.SUCCESS(upload);
    }


}