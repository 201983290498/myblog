package com.coder.blog.controller.AccountInfo;

import com.coder.commom.annotation.ResourceAcquisitionRecorder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author coder
 * @Date 2021/12/3 13:08
 * @Description
 */
@Controller
@RequestMapping("/account")
@Api("个人信息主页面相关的测试,/account字符开头")
public class DashBoardController {

    private final String resourcePrefix = "userInfo/";

    @ResourceAcquisitionRecorder(name="个人信息主页面")
    @ApiOperation(value = "个人信息主页面", notes = "支持多种方法的访问", httpMethod = "GET")
    @RequestMapping(value = "/dashBoard",method = {RequestMethod.GET,RequestMethod.POST})
    public String dashBoard(){
        return resourcePrefix + "dashBoard";
    }

}
