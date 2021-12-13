package com.coder.blog.controller.admin;

import com.coder.blog.service.UserService;
import com.coder.commom.annotation.Enum.ResourceType;
import com.coder.commom.annotation.ResourceAcquisitionRecorder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author coder
 * @Date 2021/12/12 16:05
 * @Description
 */
@Controller
@Api("user用户表模块")
@RequestMapping("/user")
public class UserTableController {

  private final UserService userService;

  public UserTableController(UserService userService) {
    this.userService = userService;
  }

  /**
   * TODO role:admin
   */
  @RequestMapping(value="/list", method = RequestMethod.GET)
  @ResourceAcquisitionRecorder(resourceType = ResourceType.HTML, role = "admin", name = "获取userTable页面")
  @ApiOperation(value = "获取userTable页面", httpMethod = "GET")
  public String userTable(ModelMap map, HttpServletRequest request){
    map.addAttribute("tableName","UserTable");
    return "/admin/userTable";
  }
}
