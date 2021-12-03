package com.coder.blog.controller;

import com.coder.commom.annotation.AccessLimit;
import com.coder.commom.annotation.ResourceAcquisitionRecorder;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author coder
 * @Date 2021/12/3 12:37
 * @Description
 */
@Controller
@Api("专门用来处理-公共的视图解析器")
public class ViewController {



    @RequestMapping("/")
    @ResourceAcquisitionRecorder(name = "主页面")
    public String index(){
        return "index";
    }

    @AccessLimit(seconds = 3)
    @ResourceAcquisitionRecorder(name = "登入页面")
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @ResourceAcquisitionRecorder(name = "错误页面")
    @RequestMapping("/error")
    public String error(){
        return "error";
    }

}
