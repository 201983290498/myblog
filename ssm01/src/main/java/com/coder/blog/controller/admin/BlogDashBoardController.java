package com.coder.blog.controller.admin;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author coder
 * @Date 2021/12/12 17:17
 * @Description
 */
@Controller
@Api("blog的控制面板")
@RequestMapping(value = "/admin")
public class BlogDashBoardController {

  @RequestMapping(value = "/blogDashBoard")
  public String dashBoard(){
    return "forward:/admin/dashBoard";
  }
}
