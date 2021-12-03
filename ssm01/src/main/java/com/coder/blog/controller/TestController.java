package com.coder.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author coder
 * @Date 2021/11/26 22:53
 * @Description
 */
@Controller
@Api("测试类")
public class TestController {

    @GetMapping("/test")
    @ApiOperation(value="测试IpConfig",httpMethod = "GET")
    public String tes010(){
        return "error";
    }
}
