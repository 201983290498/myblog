package com.coder.blog.controller.admin;

import com.coder.blog.Utils.RespMessageUtils;
import com.coder.blog.entity.User;
import com.coder.blog.service.DashBoardService;
import com.coder.commom.annotation.Enum.ResourceType;
import com.coder.commom.annotation.ResourceAcquisitionRecorder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

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

  private final DashBoardService service;

  public DashBoardController(DashBoardService dashBoardService) {
    this.service = dashBoardService;
  }

  /**
   * Dash board string.
   *
   * @return the string
   */
  @ResourceAcquisitionRecorder(name="控制信息主页面")
  @ApiOperation(value = "中台控制端主页", notes = "支持多种方法的访问", httpMethod = "GET")
  @RequestMapping(value = "/dashBoard",method = {RequestMethod.GET,RequestMethod.POST})
  public String dashBoard(){
    Subject subject = SecurityUtils.getSubject();
    System.out.println(subject.isAuthenticated());
    return resourcePrefix + "dashBoard";
  }

  @ResourceAcquisitionRecorder(resourceType = ResourceType.RECORD, name = "获取中台页面的所有信息")
  @ResponseBody
  @ApiOperation(value = "获取首页需要的信息", notes = "获取首页需要的信息", httpMethod = "GET")
  @RequestMapping("/dashBoardInfo")
  public String dashBoardInfo(HttpServletRequest request, ModelMap map){
    HttpSession session = request.getSession();
    User user = (User)session.getAttribute("user");
    if(user==null){
      RespMessageUtils.generateErrorInfo(map,new String[]{"用户为登入，session中不存在登入记录，请重新登入"});
      return "forward:/error";
    }
    Map<String, Object> dashBoardInfo = service.getDashBoardInfo(user.getUsername());
    System.out.println(dashBoardInfo);
    return RespMessageUtils.SUCCESS(dashBoardInfo);
  }


}
