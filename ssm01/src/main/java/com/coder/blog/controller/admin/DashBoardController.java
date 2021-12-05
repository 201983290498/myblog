package com.coder.blog.controller.admin;

import com.coder.commom.annotation.ResourceAcquisitionRecorder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The type Dash board controller.
 *
 * @Author coder
 * @Date 2021 /12/5 23:23
 * @Description
 */
@Controller
@RequestMapping("/admin")
@Api("超级管理员的程序")
public class DashBoardController {
  private final String resourcePrefix = "admin/";

  /**
   * Dash board string.
   *
   * @return the string
   */
  @ResourceAcquisitionRecorder(name="控制信息主页面")
  @ApiOperation(value = "中台控制端主页", notes = "支持多种方法的访问", httpMethod = "GET")
  @RequestMapping(value = "/dashBoard",method = {RequestMethod.GET,RequestMethod.POST})
  public String dashBoard(){
    return resourcePrefix + "dashBoard";
  }


}
